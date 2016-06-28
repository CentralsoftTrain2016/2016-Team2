package service;

import java.sql.SQLException;

import dao.AdDao;
import domain.Ad;

public class LoserResultAdDescriptService extends Service{

	public LoserResultAdDescriptService() throws SQLException, ClassNotFoundException{
		super();
	}

  public Ad getAdDescript(){
	  AdDao adD = new AdDao(this.con);

	  int adId = 0;//固定値
	  Ad ado = null;
	try {
		int adTotal = adD.countAD();    //広告の総数を数える
		adId = (int)(Math.random() * adTotal) + 1;  //ランダムに取得
		ado = adD.getAd(adId);      //AdDaoのgetAd呼び出し(返り値:広告のURL)

	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

	return ado;
  }
}