package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.GameDao;
import dao.MassDao;
import domain.Game;
import domain.IsFinishedEnum;
import domain.Mass;
import domain.value.ENDFLAG;
import domain.value.GAMEID;
import domain.value.PLAYERID;
import service.bean.AutoRequestBean;

public class ChangeTurnPlayerService extends Service{
	//コンストラクタ
	public ChangeTurnPlayerService() throws SQLException, ClassNotFoundException{
		super();
	}

	public ChangeTurnPlayerService(Connection con) throws SQLException, ClassNotFoundException{
		super(con);
	}

	//ターンプレイヤーを入れ替える
	public AutoRequestBean changeTurnPlayer(GAMEID gameID) throws SQLException, ClassNotFoundException{
		GameDao gdao = new GameDao(this.con);
		gdao.updateTurn(gameID);

		//bean
		AutoRequestBean arb = new AutoRequestBean();
		arb.setIsMyTurn(false);
		return arb;
	}

	//ターンプレイヤーかどうか確認
	public AutoRequestBean checkTurnPlayer(GAMEID gameID, PLAYERID playerID) throws SQLException, ClassNotFoundException{
		GameDao gdao = new GameDao(this.con);
		//gdao.updateTurn(gameID);

		//bean
		AutoRequestBean arb = new AutoRequestBean();
		arb.setIsMyTurn(gdao.isMyTurn(gameID, playerID));

		return arb;
	}

	//ターンプレイヤーかどうかorゲーム終了したかどうか確認
	public AutoRequestBean checkTurnPlayerOrGameFinished(GAMEID gameID, PLAYERID playerID) throws SQLException, ClassNotFoundException{
		GameDao gdao = new GameDao(this.con);
		Game gm = gdao.getGame(gameID.get());

		//bean
		AutoRequestBean arb = new AutoRequestBean();
		//arb.setIsMyTurn(gdao.isMyTurn(gameID, playerID));

		arb.setIsMyTurn( gm.getTURNPLAYERID().get() == playerID.get() );
		arb.setIsFinished( gm.getISFINISHED() == IsFinishedEnum.FINISHED );

		return arb;
	}

	//ターン入れ替え時orゲーム終了時に非ターンプレイヤーのマス状態を入れ替えるためのリスト取得
	public List<Mass> getAllMass(GAMEID gameID) throws SQLException, ClassNotFoundException{
		List<Mass> massList = new ArrayList<Mass>();

		MassDao mdao = new MassDao(this.con);

		for(int i=1;i<=256;i++){
			massList.add(mdao.getMass(i, gameID.get()));
		}

		//クリア時に旗立てる
		if( mdao.isAllMassClear( gameID ) ){
			for(Mass mass : massList){
				mass.setENDFLAG(new ENDFLAG(true));
			}
		}

		return massList;
	}
}
