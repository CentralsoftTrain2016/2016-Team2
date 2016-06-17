package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Quiz;
import domain.value.ANSWER;
import domain.value.QUIZDESCRIPTION;
import domain.value.QUIZID;

public class QuizDao extends Dao{

	public QuizDao(Connection con) {
		super(con);
	}
	public Quiz getDictionaryItem(int QuizID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Quiz qs = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   QUIZ "
						+ " where "
						+ "   QUIZID = ?");

			stmt.setInt(1, QuizID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをdisに格納します。 */
			while (rset.next()){
					qs = new Quiz();
					qs.setQUIZID(new QUIZID(rset.getInt(1)));
					qs.setDESCRIPTION( new QUIZDESCRIPTION(rset.getString(2)));
					qs.setANSWER(new ANSWER(rset.getInt(3)));
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

		return qs;
	}
}