package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Ad;
import domain.value.ADDESCRIPTION;
import domain.value.ADID;
import domain.value.VIEWS;


/**
 * データベースのADテーブルを操作するDAOです。
 *
 * @author junichi.furukawa
 *
 */
public class AdDao extends Dao{

	/**
	 * コンストラクタ
	 * @param con
	 */
	public AdDao(Connection con) {
		super(con);
	}

	/**
	 * ADテーブルからデータを取得します。
	 *
	 * @param adID 広告のID
	 * @return 条件に合致するデータ
	 * @throws SQLException
	 */
	public Ad getAd(int adID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Ad ad = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   AD "
						+ " where "
						+ "   ADID = ?");

			stmt.setInt(1, adID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをadに格納します。 */
			while (rset.next()){
				ad = new Ad();
				ad.setADID(new ADID(rset.getInt(1)));
				ad.setADDESCRIPTION(new ADDESCRIPTION(rset.getString(2)));
				ad.setVIEWS(new VIEWS(rset.getInt(3)));
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

		return ad;
	}

	/**
	 * ADテーブルに記録されている項目数を取得します。
	 *
	 * @return 広告の数
	 * @throws SQLException
	 */
	public int countAD() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		int adTotal = 0;


		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ " count(adid)"
						+ " from "
						+ " AD ");

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをdisに格納します。 */
			while (rset.next()){
				adTotal = rset.getInt(1);

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


		return adTotal;
	}
}

