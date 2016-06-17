package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Player;
import domain.value.CHARACTERID;
import domain.value.ANSWER;
import domain.value.ITEMID;
import domain.value.PLAYERID;
import domain.value.TIMELIMIT;
import domain.value.TOTALTIME;
import domain.value.USERID;

public class PlayerDao extends Dao{

	/**
	 * コンストラクタ
	 * @param con
	 */
	public PlayerDao(Connection con) {
		super(con);
	}

	/**
	 *
	 * @param playerID
	 * @return
	 * @throws SQLException
	 */
	public Player getPlayer(int playerID) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rset = null;
		Player pm = null;

		try {
			/* Statementの作成 */
			stmt = con.prepareStatement(
					      " select "
					    + "   *"
						+ " from "
						+ "   PLAYER "
						+ "where "
						+ "  PLAYERID =? "
						);

			//SQL文に代入
			stmt.setInt(1, playerID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();

			/* 取得したデータを表示します。 */
			while (rset.next()) {
				pm = new Player();
				pm.setPLAYERID(new PLAYERID(rset.getInt(1)));
				pm.setUSERID(new USERID(rset.getInt(2)));
				pm.setCHARACTERID(new CHARACTERID(rset.getInt(3)));
				pm.setTOTALTIME(new TOTALTIME(rset.getInt(4)));
				pm.setITEMID(new ITEMID(rset.getInt(5)));
				pm.setCOOLTIME(new ANSWER(rset.getInt(6)));
				pm.setTIMELIMIT(new TIMELIMIT(rset.getInt(7)));

				// System.out.println(rset.getString(1));
			}
		}

		catch (SQLException e) {
			throw e;
		}
		catch ( Exception e){
			throw e;
		}
		finally {

			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (rset != null) {
				rset.close();
				rset = null;
			}
		}

		return pm;
	}



}
