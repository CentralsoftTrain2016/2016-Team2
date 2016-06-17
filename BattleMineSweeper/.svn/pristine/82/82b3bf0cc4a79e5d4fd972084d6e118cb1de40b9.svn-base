package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Users;
import domain.value.LOSECOUNT;
import domain.value.PASSWORD;
import domain.value.USERID;
import domain.value.USERNAME;
import domain.value.WINCOUNT;


public class UsersDao extends Dao {
	public UsersDao(Connection con) {
		super(con);
	}


	/**
	 * 入力されたIDに該当するユーザをデータベースから検索するメソッド
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public Users getUser(int userID,String password) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rset = null;
		Users um = null;

		try {
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  "select "
						+ "   USERID"
						+ "  ,USERNAME"
						+ "  ,PASSWORD"
						+ "  ,WINCOUNT"
						+ "  ,LOSECOUNT"
						+ " from "
						+ "   USERS "
						+ "where "
						+ "   USERID =? "
						+ "   and "
						+ "  PASSWORD = ?"
						);

			//SQL文に代入
			stmt.setInt(1, userID);
			stmt.setString(2, password);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();

			/* 取得したデータを表示します。 */
			while (rset.next()) {
				um = new Users();
				um.setUSERID(new USERID(rset.getInt(1)));
				um.setUSERNAME(new USERNAME(rset.getString(2)));
				um.setPASSWORD(new PASSWORD(rset.getString(3)));
				um.setWINCOUNT(new WINCOUNT(rset.getInt(4)));
				um.setLOSECOUNT(new LOSECOUNT(rset.getInt(5)));

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

		return um;
	}


	/**
	 * 入力されたユーザをデータベースに新規登録するメソッド
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public Users createUser(int userID, String userName,String password) throws  SQLException{

		PreparedStatement stmt = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
					  "insert "
				    + "   into USERS "
					+ "("
					+ "    	 USERID "
					+ "    	,USERNAME "
					+ "   	,PASSWORD "
					+ "   	,WINCOUNT "
					+ "   	,LOSECOUNT "
					+ ")"
					+ "values "
					+ "("
					+ " 	 seq_USERID.nextval"
					+ " 	,?"
					+ "		,?"
					+ "		,0"
					+ "		,0"
					+ ")"
					);

			//SQL文に代入
			stmt.setInt(1, userID);
			stmt.setString(2, userName);
			stmt.setString(3, password);

			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("登録できません. ID:" + userID);
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
		return getUser(userID,password);
	}


	/**
	 * データベースのユーザ情報を更新するメソッド
	 * @param winUserID 勝ったユーザーのID
	 * @param loseUserID 負けたユーザーのID
	 * @throws SQLException
	 */
	public void updateResult(int winUserID, int loseUserID) throws SQLException {

		PreparedStatement wstmt = null;
		PreparedStatement lstmt = null;

		//wincountを更新-------------------------------------------------------
		try {
			/* Statementの作成 */

				wstmt = con.prepareStatement(
						   "update "
						+ "   USERS "
						+ " set "
						+ "   WINCOUNT = WINCOUNT + 1 "
						+ "where "
						+ "  USERID = ? "
						);

			// SQL文に代入
			wstmt.setInt(1, winUserID);

			/* ｓｑｌ実行 */
			int lineCount = wstmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("登録できません. ID:" + winUserID);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (wstmt != null) {
				try {
					wstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				wstmt = null;
			}
		}

		// losecountを更新------------------------------------------------------
		try {
			/* Statementの作成 */
				lstmt = con.prepareStatement(
						   "update "
						+ "   USERS "
						+ " set "
						+ "   LOSECOUNT = LOSECOUNT + 1 "
						+ "where "
						+ "  USERID = ? "
						);

			// SQL文に代入
			lstmt.setInt(1, loseUserID);

			/* ｓｑｌ実行 */
			int lineCount = lstmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("登録できません. ID:" + loseUserID);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (lstmt != null) {
				try {
					lstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				lstmt = null;
			}
		}
	}


	public void updateSingleExplosionResult(USERID userid) throws SQLException {
		PreparedStatement stmt = null;
		// singlelosecountを更新------------------------------------------------------
		try {
			/* Statementの作成 */
				stmt = con.prepareStatement(
						   "update "
						+ "   USERS "
						+ " set "
						+ "   SINGLELOSECOUNT = SINGLELOSECOUNT + 1 "
						+ "where "
						+ "  USERID = ? "
						);

			// SQL文に代入
			stmt.setInt(1, userid.get());

			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("登録できません. ID:" + userid.get());
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


	public void updateSignleAllOpenResult(USERID userID) throws SQLException {
		PreparedStatement stmt = null;
		// singlewincountを更新------------------------------------------------------
		try {
			/* Statementの作成 */
				stmt = con.prepareStatement(
						   "update "
						+ "   USERS "
						+ " set "
						+ "   SINGLEWINCOUNT = SINGLEWINCOUNT + 1 "
						+ "where "
						+ "  USERID = ? "
						);

			// SQL文に代入
			stmt.setInt(1, userID.get());

			/* ｓｑｌ実行 */
			int lineCount = stmt.executeUpdate();
			if (lineCount != 1) {
				throw new SQLException("登録できません. ID:" + userID.get());
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
