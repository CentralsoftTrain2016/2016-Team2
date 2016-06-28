package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GameDao;
import dao.ItemDao;
import dao.PlayerDao;
import domain.ClickActionInterface;
import domain.Game;
import domain.Item;
import domain.Mass;
import domain.Player;
import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.MASSID;
import domain.value.MASSLIST;
import domain.value.PLAYERID;
import service.bean.ClickResultBean;
//author:mori
public class ItemUseService implements ClickActionInterface{

	public ItemUseService() {
		super();
	}

	// アイテム使用
	public ClickResultBean clickAction(MASSID massID,
										GAMEID gameID,
										int turnTotalTime,
										ClickPlayerID clickPlayerID,
										Connection con
										)throws SQLException, ClassNotFoundException{

		System.out.println("アイテムつかうよー");

		PlayerDao pdao = new PlayerDao(con);
		Player player = pdao.getPlayer(clickPlayerID.get());
		int itemid = player.getITEMID().get();
		ItemDao idao = new ItemDao(con);
		Item item = idao.getItem(itemid);
		item.getITEMENUM().itemAction(con,gameID,new PLAYERID(clickPlayerID.get()));
		ClickResultBean bean = new ClickResultBean();
		bean.setMASSLIST(new MASSLIST(new ArrayList<Mass>()));

		//自分プレイヤー
		bean.setMyPlayer( pdao.getPlayer(clickPlayerID.get()) );

		//相手プレイヤーID取得
		GameDao gdao = new GameDao(con);
		Game gm = gdao.getGame(gameID.get());
		int player1ID = gm.getPLAYER1ID().get();
		int player2ID = gm.getPLAYER2ID().get();

		PLAYERID enemyID = null;

		if(player1ID == clickPlayerID.get()){
			enemyID = new PLAYERID(player2ID);
		}else{
			enemyID = new PLAYERID(player1ID);
		}

		bean.setEnemyPlayer( pdao.getPlayer(enemyID.get()) );

		return  bean;
	}


}
