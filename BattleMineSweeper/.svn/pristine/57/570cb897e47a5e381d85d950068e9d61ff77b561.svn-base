package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Item;
import domain.value.EFFICACY;
import domain.value.ITEMID;
import domain.value.ITEMNAME;

public class ItemDao extends Dao{

	public ItemDao(Connection con) {
		super(con);
	}
	public Item getDictionaryItem(int itemID) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Item is = null;

		try{
			/* Statementの作成 */
			stmt = con.prepareStatement(
						  " select "
						+ "   *"
						+ " from "
						+ "   ITEM "
						+ " where "
						+ "   ITEMID = ?");

			stmt.setInt(1, itemID);

			/* ｓｑｌ実行 */
			rset = stmt.executeQuery();
			/* 取得したデータをdisに格納します。 */
			while (rset.next()){
					is = new Item();
					is.setITEMID(new ITEMID(rset.getInt(1)));
					is.setITEMNAME( new ITEMNAME(rset.getString(2)));
					is.setEFFICACY(new EFFICACY(rset.getString(3)));
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

		return is;
	}
}
