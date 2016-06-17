package service;

import java.sql.SQLException;

import dao.DictionaryItemDao;
import domain.DictionaryItem;

public class WinnerResultInfoDescriptService extends Service{

	public WinnerResultInfoDescriptService() throws SQLException, ClassNotFoundException{
		super();
	}

  public DictionaryItem getInfoDescript(){
	  DictionaryItemDao dID = new DictionaryItemDao(this.con);

	  int informationID = 1;//固定値
	  DictionaryItem dis = null;
	try {
		dis = dID.getDictionaryItem(informationID);//DictionaryItemDaoのgetDictionaryItem呼び出し

	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	return dis;
  }
}
