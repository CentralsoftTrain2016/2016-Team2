package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.AnswerEnum;
import domain.Quiz;
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
					qs.setANSWER(AnswerEnum.getAnswerByValue(rset.getInt(3)));
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

	// クイズIDのリストを取得
	public List<Integer> getQuizQuantity() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<Integer>quiznumlist = new ArrayList<Integer>();

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   QUIZ ");


			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			while (rset.next()){
				quiznumlist.add(rset.getInt(1));
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

		System.out.println(quiznumlist);

		return quiznumlist;
	}
}