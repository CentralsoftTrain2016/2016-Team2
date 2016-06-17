package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.GameDao;
import dao.MassDao;
import domain.ClickActionInterface;
import domain.Game;
import domain.Mass;
import domain.value.ClickPlayerID;
import domain.value.ENDFLAG;
import domain.value.GAMEID;
import domain.value.ISFINISH;
import domain.value.ISOPEN;
import domain.value.MASSID;
import domain.value.MASSLIST;
import domain.value.PLAYMODE;
import service.bean.ClickResultBean;
import service.bean.WinLoseBean;

public class ClickActionService implements ClickActionInterface  {
	public ClickActionService(){
		super();

	}
//----------------------------------------------------------------------------------------------------------------------
	// セッションからとってくる
	public ClickResultBean clickAction(MASSID massID, GAMEID gameID, ClickPlayerID clickPlayerID, Connection con)
			throws SQLException, ClassNotFoundException{
		ClickResultBean bean = new ClickResultBean();
		List<Mass> MassList = new ArrayList<Mass>();
		MassDao msdao = new MassDao(con);
		Mass ms = msdao.getMass(massID.get(), gameID.get());
		GameDao gmdao = new GameDao(con);
		Game gm = gmdao.getGame(gameID.get());
		PLAYMODE playMode = gm.getPLAYMODE();

		//turnPlayerじゃないプレイヤーがアクセスしたらそのままリターン
		if( gm.getTURNPLAYERID().get() != clickPlayerID.get() ){
			System.out.println("きみのばんじゃないだろ！");
			return bean;
		}

		if(ms.getISOPEN().get()){														//既に開いていたマスの場合は空のリストを返却
			System.out.println("すでにあいてるよー");
			return bean;
		}
		//地雷爆発処理
		if(ms.getISEXISTMINE().get()){
			WinLoseBean winlose = playMode.explosionMine(ms.getGAMEID(),con);
			bean.setWinPlayerID(winlose.getWinPlayerID() );
			bean.setLosePlayerID(winlose.getLosePlayerID());

			// 変更後
			msdao.updateAllISOPEN(ms.getGAMEID().get());
			MassList = msdao.getAllMass(ms.getGAMEID().get());
			// 変更前
			//MassList.add(ms);
			bean.setMASSLIST( new MASSLIST(MassList) );
			bean.setISFINISH(new ISFINISH(true));
			return bean;
		}

		//開いたマスをKekkaListに格納
		List<Mass> KekkaList = massOpen(
															ms.getMASSID().get(),
															ms.getGAMEID().get(),
															MassList,
															con
															);
		// 下に移動しました
		//bean.setMASSLIST( new MASSLIST(KekkaList) );

		//地雷以外の全てのマスがオープンしたときの処理
		if(  msdao.isAllMassClear( gameID )  ){
			//if( true ){
			System.out.println("全部あいたよ！");
			WinLoseBean winlose= playMode.allOpen(gameID , con);
			bean.setWinPlayerID(  winlose.getWinPlayerID() );
			bean.setLosePlayerID( winlose.getLosePlayerID() );

			//追加
			msdao.updateAllISOPEN(ms.getGAMEID().get());
			MassList = msdao.getAllMass(ms.getGAMEID().get());
			for (Mass mass : MassList){
				mass.setENDFLAG(new ENDFLAG(true));
				}
			bean.setMASSLIST( new MASSLIST(MassList) );

			bean.setISFINISH(new ISFINISH(true));
			return bean;
		}

		//プレイモードに応じて次のターンの準備
		playMode.prepareNextTurn(gameID,con);

		bean.setMASSLIST( new MASSLIST(KekkaList) );


		//ここからマスの状態によって、各イベントに分岐

		return bean;
	}
//ClickActionメソッド

//-------------------------------------------------------------------------------------
//爆弾が無いことが保障されるので好き勝手やろう
//開閉状態をupdateして、listに格納
	private List<Mass> massOpen(int mymassID,int gameID, List<Mass> MassList, Connection con)
			throws SQLException, ClassNotFoundException{
		try{
			MassDao massdao =  new MassDao(con);
			Mass ms = massdao.getMass( mymassID, gameID);				//クリックされたマスデータの取得
			if(ms.getISOPEN().get()){
				//System.out.println("あいてたー"+mymassID);
				return MassList;
			}
			massdao.updateISOPEN(ms.getMASSID().get(),ms.getGAMEID().get());	//閉まっていたマスを開ける
			ms.setISOPEN(new ISOPEN(true));

			MassList.add(ms);									//開けたマスを格納していく

		if(ms.getAROUNDMINES().get() == 0){		//周囲に地雷が無いとき、周囲のマスも再帰的に開ける
			if(16 < mymassID ){
				if(mymassID % 16 != 1){
					massOpen(mymassID-17,gameID, MassList, con);
				}
				massOpen(mymassID-16,gameID, MassList, con);
				if(mymassID % 16 != 0){
					massOpen(mymassID-15,gameID, MassList, con);
				}
			}
			if( mymassID < 240 ){
				if(mymassID % 16 != 1){
					massOpen(mymassID+15,gameID, MassList, con);
				}
				massOpen(mymassID+16,gameID, MassList, con);
				if(mymassID % 16 != 0){
					massOpen(mymassID+17,gameID, MassList, con);
				}
			}
			if(mymassID % 16 != 1){
				massOpen(mymassID-1,gameID, MassList, con);
			}
			if(mymassID % 16 != 0){
				massOpen(mymassID+1,gameID, MassList, con);
			}
		}
		//	周囲に地雷があるときはそのままリターン
		return MassList;
		}finally{
			//System.out.println("finallyを通りました。");
		}

	}//massOpenメソッド





}
