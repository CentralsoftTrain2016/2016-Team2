package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Character;
import domain.value.CHARACTERID;
import domain.value.CHARACTERNAME;
import domain.value.SKILL;
import domain.value.SKILLCOOLTIME;


/**
 * データベースのCHARACTERテーブルを操作するDAOです。
 *
 * @author junichi.furukawa
 *
 */
public class CharacterDao extends Dao{

	public CharacterDao(Connection con) {
		super(con);
	}

	/**
	 * 指定したキャラクターの情報を取得するメソッドです。
	 *
	 * @param characterID キャラクターID
	 * @return キャラクターのデータ
	 * @throws SQLException
	 */
	public Character getCharacterInfo(int characterID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Character cs = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   CHARACTER "
						+ " where "
						+ "   CHARACTERID = ?");

			stmt.setInt(1, characterID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをdisに格納します。 */
			while (rset.next()){
					cs = new Character();
					cs.setCHARACTERID(new CHARACTERID(rset.getInt(1)));
					cs.setCHARACTERNAME( new CHARACTERNAME(rset.getString(2)));
					cs.setSKILL(new SKILL(rset.getInt(3)));
					cs.setSKILLCOOLTIME(new SKILLCOOLTIME(rset.getInt(4)));
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

		return cs;
	}
}
