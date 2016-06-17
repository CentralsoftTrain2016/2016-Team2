package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.DictionaryKey;
import domain.value.INFORMATIONID;
import domain.value.USERID;

public class DictionaryKeyDao extends Dao {

	public DictionaryKeyDao(Connection con) {
		super(con);
	}

	/**
	 *
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public DictionaryKey getDictionaryKey(int userID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		DictionaryKey dks = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   DICTIONARYKEY "
						+ " where "
						+ "   USERID = ? ");

			stmt.setInt(1, userID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをgsに格納します。 */
			while (rset.next()){
					dks = new DictionaryKey();
					dks.setINFORMATIONID(new INFORMATIONID(rset.getInt(1)));
					dks.setUSERID(new USERID(rset.getInt(2)));
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

		return dks;
	}



	/**
	 * ユーザーが地雷知識を見るためのカギを作ります。
	 *
	 * @param informationID
	 * @param userID
	 * @throws SQLException
	 */
	public void createKey(int informationID, int userID) throws  SQLException{

		PreparedStatement stmt = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
					  "insert "
				    + "   into DICTIONARYKEY "
					+ "("
					+ "    	 INFORMATIONID "
					+ "    	,USERID "
					+ ")"
					+ "values "
					+ "("
					+ " 	 ?"
					+ " 	 ?"
					+ ")"
					);

			//SQL文に代入
			stmt.setInt(1, informationID);
			stmt.setInt(2, userID);

			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("カギを登録できません. ID:" + userID);
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
}
