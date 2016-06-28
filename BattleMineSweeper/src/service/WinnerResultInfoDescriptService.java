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

	  int informationId = 0;  //初期化
	  DictionaryItem dis = null;
	try {
		int dictionaryTotal = dID.dictionaryCount();             //図鑑の総数を数える
		informationId = (int)(Math.random() * dictionaryTotal) + 1;  //ランダムに取得
		dis = dID.getDictionaryItem(informationId);              //DictionaryItemDaoのgetDictionaryItem呼び出し

	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	return dis;
  }
}
