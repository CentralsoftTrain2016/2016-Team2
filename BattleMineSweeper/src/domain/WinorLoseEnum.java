package domain;

import java.sql.Connection;
import java.sql.SQLException;

import dao.GameDao;
import dao.PlayerDao;
import dao.UsersDao;
import domain.value.GAMEID;
import domain.value.PLAYERID;

public enum WinorLoseEnum {
	WIN{
		public void updateResult(GAMEID gameID, PLAYERID playerID, Connection con) throws SQLException{
			GameDao gamedao =  new GameDao(con);
			Game gm = gamedao.getGame( gameID.get());

			int player1ID = gm.getPLAYER1ID().get();
			int player2ID = gm.getPLAYER2ID().get();

			PLAYERID enemyID = null;

			if(player1ID == playerID.get()){
				enemyID = new PLAYERID(player2ID);
			}else{
				enemyID = new PLAYERID(player1ID);
			}

			//プレイヤーIDからユーザIDを取得
			PlayerDao pdao = new PlayerDao(con);
			Player winPlayer = pdao.getPlayer(playerID.get());
			Player losePlayer = pdao.getPlayer(enemyID.get());

			//FINALWINPLAYERIDがnull以外ならすでにゲームが終了しているのでDBに勝敗を登録しない
			if( gm.getFINALWINPLAYERID().get() != 0 ) return;

			//FINALWINPLAYERID更新
			gamedao.updateResult(gameID, winPlayer.getPLAYERID());

			//ユーザのスコアを更新
			UsersDao udao = new UsersDao(con);

			udao.updateResult(winPlayer.getUSERID().get(), losePlayer.getUSERID().get());

			return;
		}
	},
	LOSE{
		public void updateResult(GAMEID gameID, PLAYERID playerID, Connection con) throws SQLException{
			GameDao gamedao =  new GameDao(con);
			Game gm = gamedao.getGame( gameID.get());

			int player1ID = gm.getPLAYER1ID().get();
			int player2ID = gm.getPLAYER2ID().get();

			PLAYERID enemyID = null;

			if(player1ID == playerID.get()){
				enemyID = new PLAYERID(player2ID);
			}else{
				enemyID = new PLAYERID(player1ID);
			}

			//プレイヤーIDからユーザIDを取得
			PlayerDao pdao = new PlayerDao(con);
			Player winPlayer = pdao.getPlayer(enemyID.get());
			Player losePlayer = pdao.getPlayer(playerID.get());

			//FINALWINPLAYERIDがnull以外ならすでにゲームが終了しているのでDBに勝敗を登録しない
			if( gm.getFINALWINPLAYERID().get() != 0 ) return;

			//FINALWINPLAYERID更新
			gamedao.updateResult(gameID, winPlayer.getPLAYERID());

			//ユーザのスコアを更新
			UsersDao udao = new UsersDao(con);

			udao.updateResult(winPlayer.getUSERID().get(), losePlayer.getUSERID().get());

			return;
		}
	};

	public abstract void updateResult(GAMEID gameID, PLAYERID playerID, Connection con) throws SQLException;
}
