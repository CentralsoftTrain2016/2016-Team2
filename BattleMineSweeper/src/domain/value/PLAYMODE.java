package domain.value;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.GameDao;
import dao.PlayerDao;
import domain.Game;
import domain.Player;
import domain.WinorLoseEnum;
import service.ChangeTurnPlayerService;
import service.UpdateWinLoseCountService;
import service.bean.WinLoseBean;

public enum PLAYMODE {
	SINGLE_MODE("1"){
		public WinLoseBean explosionMine(GAMEID gameID, Connection con)
				throws SQLException, ClassNotFoundException{
			System.out.println("エクスプローーージョン！！！");

			GameDao gamedao =  new GameDao(con);
			Game gm = gamedao.getGame( gameID.get());				//クリックされたマスデータの取得

			int player1ID = gm.getPLAYER1ID().get();
			//プレイヤーIDからユーザIDを取得
			PlayerDao pdao = new PlayerDao(con);
			Player Player1 = pdao.getPlayer(player1ID);

			//ユーザのスコアを更新(負け数＋１)
			//UsersDao udao = new UsersDao(con);
			//udao.updateSingleExplosionResult( Player1.getUSERID() );

			WinLoseBean winlose = new WinLoseBean();
			winlose.setLosePlayerID( new LosePlayerID(player1ID) );
			return winlose;
		}

		@Override
		public WinLoseBean allOpen(GAMEID gameID, Connection con) throws SQLException, ClassNotFoundException {
			GameDao gamedao =  new GameDao(con);
			Game gm = gamedao.getGame( gameID.get());				//クリックされたマスデータの取得

			int player1ID = gm.getPLAYER1ID().get();
			WinLoseBean winlose = new WinLoseBean();

			//プレイヤーIDからユーザIDを取得
			PlayerDao pdao = new PlayerDao(con);
			Player player1 = pdao.getPlayer(player1ID);
			//ユーザのスコアを更新(勝ち数＋１)
			//UsersDao udao = new UsersDao(con);
			//udao.updateSignleAllOpenResult( player1.getUSERID() );
			winlose.setWinPlayerID( new WinPlayerID(player1ID) );
			return winlose;
		}

		public void prepareNextTurn(GAMEID gameID, Connection con,PLAYERID playerID, int turnTotalTime) throws SQLException,ClassNotFoundException{

		}

	},


	BATTLE_MODE("2"){
		public WinLoseBean explosionMine(GAMEID gameID, Connection con)
				throws SQLException, ClassNotFoundException{
			try{
				System.out.println("エクスプローーージョン！！！");
				GameDao gamedao =  new GameDao(con);
				Game gm = gamedao.getGame( gameID.get());				//クリックされたマスデータの取得

				int player1ID = gm.getPLAYER1ID().get();
				int player2ID = gm.getPLAYER2ID().get();
				int turnPlayerID = gm.getTURNPLAYERID().get();				//爆発時のターンプレイヤー＝負け


				UpdateWinLoseCountService uwlc = new UpdateWinLoseCountService(con);
				WinLoseBean winlose = uwlc.updateWinLoseCount(player1ID, player2ID, turnPlayerID);
				//uwlc.end();
				return winlose;

			}finally{

			}
		}

		@Override
		public WinLoseBean allOpen(GAMEID gameID, Connection con) throws SQLException, ClassNotFoundException {

			GameDao gamedao =  new GameDao(con);
			Game gm = gamedao.getGame( gameID.get());				//クリックされたマスデータの取得

			int player1ID = gm.getPLAYER1ID().get();
			int player2ID = gm.getPLAYER2ID().get();

			//プレイヤーIDからユーザIDを取得
			PlayerDao pdao = new PlayerDao(con);
			Player player1 = pdao.getPlayer(player1ID);
			Player player2 = pdao.getPlayer(player2ID);

			WinLoseBean winlose = new WinLoseBean();


			//ユーザのスコアを更新
			//UsersDao udao = new UsersDao(con);
			//Users u1 = udao.getUser(Player1.getPLAYERID().get);
			//Users u2 = udao.getUser(Player2.getPLAYERID().get);

			//経過時間で勝敗を判定
			if( player1.getTOTALTIME().get() >= player2.getTOTALTIME().get() ){  												//p1が敗北した場合
					//↓コメントアウトしました 森
					//udao.updateResult(player2.getUSERID().get(), player1.getUSERID().get());

					//p2は図鑑がひとつ開放される（被りあり）
					//p1は広告が表示される

				WinorLoseEnum.LOSE.updateResult(gameID, player1.getPLAYERID(), con);

					winlose.setWinPlayerID(new WinPlayerID(player2ID));
					winlose.setLosePlayerID( new LosePlayerID(player1ID) );
					System.out.println(player1.getTOTALTIME().get());
					System.out.println(player2.getTOTALTIME().get());

			}else if( player1.getTOTALTIME().get() < player2.getTOTALTIME().get() ){												//p2が敗北した場合
				    //↓コメントアウトしました 森
					//udao.updateResult(player1.getUSERID().get(), player2.getUSERID().get());

					//p2は図鑑がひとつ開放される（被りあり）
					//p1は広告が表示される

				WinorLoseEnum.LOSE.updateResult(gameID, player2.getPLAYERID(), con);

					winlose.setWinPlayerID(new WinPlayerID(player1ID));
					winlose.setLosePlayerID( new LosePlayerID(player2ID) );
					System.out.println(player1.getTOTALTIME().get());
					System.out.println(player2.getTOTALTIME().get());
			}

			return winlose;
		}

		public void prepareNextTurn(GAMEID gameID, Connection con,PLAYERID playerID, int turnTotalTime) throws SQLException,ClassNotFoundException{
			ChangeTurnPlayerService ctps = new ChangeTurnPlayerService(con);
			ctps.changeTurnPlayer(gameID);

			//クールタイム減少処理
			PlayerDao pdao = new PlayerDao(con);
			pdao.decreaseCoolTime(gameID, 1);

			// トータルタイム時間の追加
			pdao.totalTime(turnTotalTime, playerID);
		}

	};

	//PLAYMODEのなかのいろいろ
	private String value;
	private static Map<String, PLAYMODE> map = new HashMap<String, PLAYMODE>();

	private PLAYMODE(String value) {
		this.value = value;
	}

	static{
		map.put("1", SINGLE_MODE);
		map.put("2", BATTLE_MODE);
	}

	public String get() {
		return value;
	}

	public static PLAYMODE getPLAYMODEByValue(String value){
		return map.get(value);
	}

	public abstract WinLoseBean explosionMine(GAMEID gameID,Connection con) throws SQLException,ClassNotFoundException ;
	public abstract WinLoseBean allOpen(GAMEID gameID,Connection con) throws SQLException,ClassNotFoundException ;
	public abstract void prepareNextTurn(GAMEID gameID, Connection con,PLAYERID playerID, int turnTotalTime) throws SQLException,ClassNotFoundException ;


}

