package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Game;
import domain.IsFinishedEnum;
import domain.value.ADID;
import domain.value.DIFFICULTYID;
import domain.value.FINALWINPLAYERID;
import domain.value.GAMEID;
import domain.value.PLAYER1ID;
import domain.value.PLAYER2ID;
import domain.value.PLAYERID;
import domain.value.PLAYMODE;
import domain.value.TURNPLAYERID;
import domain.value.TURNSTARTTIME;

public class GameDao extends Dao {

	/**
	 * コンストラクタ
	 *
	 * @param con
	 */
	public GameDao(Connection con) {
		super(con);
	}

	/**
	 * GAMEテーブルからデータを取得します。
	 *
	 * @param gameID ゲームのID
	 * @param player1ID プレイヤー1のID
	 * @param player2ID プレイヤー2のID
	 * @param difficultyID 難易度のID
	 *
	 * @return 条件に合致するデータ
	 *
	 * @throws SQLException
	 */
	public Game getGame(int gameID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Game gs = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   GAME "
						+ " where "
						+ "   GAMEID = ? ");

			stmt.setInt(1, gameID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをgsに格納します。 */
			while (rset.next()){
					gs = new Game();
					gs.setGAMEID(new GAMEID(rset.getInt(1)));
					gs.setPLAYER1ID(new PLAYER1ID(rset.getInt(2)) );
					gs.setPLAYER2ID(new PLAYER2ID( rset.getInt(3)));
					gs.setDIFFICULTYID(new DIFFICULTYID(rset.getInt(4)));
					gs.setTURNPLAYERID(new TURNPLAYERID(rset.getInt(5)));
					gs.setFINALWINPLAYERID(new FINALWINPLAYERID(rset.getInt(6)));
					gs.setADID(new ADID(rset.getInt(7)) );
					gs.setPLAYMODE( PLAYMODE.getPLAYMODEByValue(rset.getString(8)) );
					gs.setTURNSTARTTIME(new TURNSTARTTIME( rset.getString(9) ));
					gs.setISFINISHED(IsFinishedEnum.valueOf( rset.getString(10) ));
				}
		  }

		catch (SQLException e) {
			throw e;
		}
		finally{
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (rset != null) {
				rset.close();
				rset = null;
			}
		}

		return gs;
	}

	/*private int getGameId(){//GameIdを順番に返す
		 Statementの作成
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			stmt = con.prepareStatement(
					  "select "
					+ "SEQ_GAMEID.nextval "
					+ "from "
					+ "dual ");

			/* ｓｑｌ実行
			rset = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return ;
	} */

	/**
	 * ゲームを生成します。
	 * ※gameをリストで持ってくるかはわからないけど、とりあえず形だけつくってみた
	 *
	 * @param gameList
	 */
	public void createGame(Game game)throws SQLException{

		//Game game = new Game();
		int gameID = game.getGAMEID().get();
		int player1ID = game.getPLAYER1ID().get();
		int player2ID = game.getPLAYER2ID().get();
		int difficultyID = game.getDIFFICULTYID().get();
		int turnPlayerID = game.getTURNPLAYERID().get();
		int adID = game.getADID().get();
		String playMode = game.getPLAYMODE().get();

		PreparedStatement stmt = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
					  "insert "
				    + "   into GAME "
					+ "("
					+ "    	 GAMEID "
					+ "    	,PLAYER1ID "
					+ "   	,PLAYER2ID "
					+ "   	,DIFFICULTYID "
					+ "   	,TURNPLAYERID "
					+ "   	,ADID "
					+ "   	,PLAYMODE "
					+ "   	,ISFINISHED "
					+ ")"
					+ "values "
					+ "("
					+ " 	 SEQ_GAMEID.nextval"
					+ " 	,?"
					+ "		,?"
					+ "		,?"
					+ "		,?"
					+ "		,?"
					+ "		,?"
					+ "		,?"
					+ ")"
					);

			//SQL文に代入
			//stmt.setInt(1, gameID);
			stmt.setInt(1, player1ID);
			stmt.setInt(2, player2ID);
			stmt.setInt(3, difficultyID);
			stmt.setInt(4, turnPlayerID);
			stmt.setInt(5, adID);
			stmt.setString(6, playMode);
			stmt.setString(7, IsFinishedEnum.UNFINISHED.name());


			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("登録できません. ID:" + gameID);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				stmt = null;
			}
		}

	}


	/*TURNPLAYER更新用のSQL文*/
	private static final String TURNPLAYER_CHANGE_SQL =
		      " UPDATE"
	    	+ "   GAME"
			+ " SET "
			+ "   TURNPLAYERID ="
			+ "     CASE TURNPLAYERID"
			+ "       WHEN PLAYER1ID THEN PLAYER2ID"
			+ "       WHEN PLAYER2ID THEN PLAYER1ID"
			+ "     END,"
			+ "   TURNSTARTTIME = TO_CHAR( SYSTIMESTAMP, 'YYYY/MM/DD HH24:MI:SS.FF3')"
			+ " WHERE"
			+ "   GAMEID = ?";

	/*TURNPLAYER確認用のSQL文*/
	private static final String TURNPLAYER_CHECK_SQL =
		      " SELECT"
	    	+ "   TURNPLAYERID"
			+ " FROM "
			+ "   GAME"
			+ " WHERE"
			+ "   GAMEID = ?";

	/**
	 * マスを掘れるプレイヤーを切り替えます。
	 *
	 * @param gameID
	 * @throws SQLException
	 */
	public void updateTurn(GAMEID gameID_p)throws SQLException{

		PreparedStatement stmt = null;

		try {
			/* Statementの作成 */
			stmt = con.prepareStatement(TURNPLAYER_CHANGE_SQL);
			stmt.setInt(1,gameID_p.get());
			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("更新するゲームがありません. ID:" + gameID_p);
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				stmt = null;
			}
		}
	}

	public boolean isMyTurn(GAMEID gameID_p, PLAYERID playerID_p) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet rset = null;
		int turnPlayerID = -1;

		try {
			/* Statementの作成 */
			stmt = con.prepareStatement(TURNPLAYER_CHECK_SQL);

			stmt.setInt(1,gameID_p.get());

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();

			if( rset.next() ){
				turnPlayerID = rset.getInt(1);
			}else{
				throw new SQLException("ゲームがありません. ID:" + gameID_p);
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				stmt = null;
			}
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				rset = null;
			}
		}

		//ターンプレイヤーIDと自分のIDが同じならtrue
		if(turnPlayerID == playerID_p.get()){
			return true;
		}

		return false;
	}


	/*TURNPLAYER確認用のSQL文*/
	private static final String FINALWINPLAYERID_UPDATE_SQL =
		      " UPDATE"
	    	+ "   GAME"
			+ " SET "
			+ "   FINALWINPLAYERID = ?,"
			+ "   ISFINISHED = ?"
			+ " WHERE"
			+ "   GAMEID = ?";

	/**
	 * 勝敗結果を反映します。
	 * @param gameList
	 * @throws SQLException
	 */
	public void updateResult(GAMEID gameID_p, PLAYERID winPlayerID_p)throws SQLException{
		PreparedStatement stmt = null;

		try {
			/* Statementの作成 */
			stmt = con.prepareStatement(FINALWINPLAYERID_UPDATE_SQL);

			stmt.setInt(1,winPlayerID_p.get());
			stmt.setString(2,IsFinishedEnum.FINISHED.name());
			stmt.setInt(3,gameID_p.get());

			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();

			if (lineCount != 1) {
				throw new SQLException("更新するゲームがありません. ID:" + gameID_p);
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				stmt = null;
			}
		}
	}

	/**
	 * ゲームIDからを反映します。
	 * @param gameList
	 * @throws SQLException
	 */
//	public void updateResult(GAMEID gameID_p, PLAYERID winPlayerID_p)throws SQLException{
//		PreparedStatement stmt = null;
//
//		try {
//			/* Statementの作成 */
//			stmt = con.prepareStatement(FINALWINPLAYERID_UPDATE_SQL);
//
//			stmt.setInt(1,winPlayerID_p.get());
//			stmt.setString(2,IsFinishedEnum.FINISHED.name());
//			stmt.setInt(3,gameID_p.get());
//
//			/* ｓｑｌ実行 */
//			int lineCount = stmt.executeUpdate();
//
//			if (lineCount != 1) {
//				throw new SQLException("更新するゲームがありません. ID:" + gameID_p);
//			}
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//					throw e;
//				}
//				stmt = null;
//			}
//		}
//	}

}
