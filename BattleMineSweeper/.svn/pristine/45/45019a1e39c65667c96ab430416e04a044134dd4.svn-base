package domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import dao.GameDao;
import dao.PlayerDao;
import domain.value.COOLTIME;
import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.ITEMID;
import domain.value.PLAYERID;

public enum ItemEnum {
		PASS_ITEM(1) {
			@Override

			// パスを行う
			public void itemAction(Connection con ,GAMEID gameid, PLAYERID playerid) throws SQLException ,ClassNotFoundException{
				GameDao gdao = new GameDao(con);
				gdao.updateTurn(gameid);
				PlayerDao pdao = new PlayerDao(con);
				pdao.updateItemId(new ClickPlayerID(playerid.get()), new ITEMID(0));

			}

			@Override
			public String getItemURL() {
				return "itemImg/Pass.png";
			}


		},
		SKILLCHARGE_ITEM(2) {
			@Override

			// スキルをチャージする
			public void itemAction(Connection con ,GAMEID gameid, PLAYERID playerid) throws SQLException, ClassNotFoundException  {
				PlayerDao pdao = new PlayerDao(con);
				pdao.setCoolTime(new ClickPlayerID(playerid.get()), new COOLTIME(0));
				pdao.updateItemId(new ClickPlayerID(playerid.get()), new ITEMID(0));
			}

			@Override
			public String getItemURL() {
				return "itemImg/SkillCharge.jpg";
			}
		},
		TIMERPURAS_ITEM(3) {
			@Override

			// 10秒プラスする。
			public void itemAction(Connection con ,GAMEID gameid, PLAYERID playerid) throws SQLException ,ClassNotFoundException{
				PlayerDao pdao = new PlayerDao(con);
				pdao.updateItemId(new ClickPlayerID(playerid.get()), new ITEMID(0));
			}

			@Override
			public String getItemURL() {
				return "itemImg/TimePlus.png";
			}
		} ;

	private int id;

	private static Map<Integer,ItemEnum>map = new Hashtable<Integer,ItemEnum>();
	static{
		map.put(1, PASS_ITEM);
		map.put(2, SKILLCHARGE_ITEM);
		map.put(3, TIMERPURAS_ITEM);
	}

	public static ItemEnum getItemEnum(int itemid){
		return map.get(itemid);
	}


	public abstract void itemAction(Connection con ,GAMEID gameid , PLAYERID playerid)throws SQLException ,ClassNotFoundException;
	public abstract String getItemURL();

	private ItemEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
