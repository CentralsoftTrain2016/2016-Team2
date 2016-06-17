package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Matching;
import domain.value.DIFFICULTYID;
import domain.value.ISMATCH;
import domain.value.MATCHINGID;
import domain.value.PLAYERID;
public class MatchingDao extends Dao{

	/**
	 * コンストラクタ
	 * @param con
	 */
	public MatchingDao(Connection con) {
		super(con);
	}

	public Matching getMatching(int matchingID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Matching ms = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   MATCHING "
						+ " where "
						+ "   MATCHINGID = ?");

			stmt.setInt(1, matchingID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをdisに格納します。 */
			while (rset.next()){
					ms = new Matching();
					ms.setMATCHINGID(new MATCHINGID(rset.getInt(1)));
					ms.setPLAYERID(new PLAYERID(rset.getInt(2)));
					ms.setDIFFICULTYID(new DIFFICULTYID(rset.getInt(3)));
					ms.setISMATCH(new ISMATCH(rset.getBoolean(4)));
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

		return ms;
	}


	/**
	 *
	 * @param matchingID
	 * @param playerID
	 * @param difficulityID
	 * @throws SQLException
	 */
	public void createMatching(int matchingID, int playerID,int difficulityID) throws  SQLException{

		PreparedStatement stmt = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
					  "insert "
				    + "   into MATCHING "
					+ "("
					+ "    	 MATCHINGID "
					+ "   	,PLAYERID "
					+ "   	,DIFFICULTYID "
					+ "   	,ISMATCH "
					+ ")"
					+ "values "
					+ "("
					+ " 	 ?"
					+ " 	 ?"
					+ "		,?"
					+ "		,FALSE"
					+ ")"
					);

			//SQL文に代入
			stmt.setInt(1, matchingID);
			stmt.setInt(2, playerID);
			stmt.setInt(3, difficulityID);

			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("マッチングに失敗しました。");
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

	/**
	 * マッチング状態をTRUEにします。
	 * @param matchingID
	 * @throws SQLException
	 */
	public void updateIsMatch(int matchingID) throws SQLException {

		PreparedStatement stmt = null;

		try {
			/* Statementの作成 */
			stmt = con.prepareStatement(
					  " UPDATE "
					+ "   MATCHING"
					+ " SET "
					+ "    ISMATCH = true"
					+ "  WHERE"
					+ "    MATCHINGID = ?");

			stmt.setInt(1, matchingID);

			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("更新するマッチングがありません. ID:" + matchingID);
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
}
