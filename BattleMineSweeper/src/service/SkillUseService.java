package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GameDao;
import dao.PlayerDao;
import domain.CharacterSkillEnum;
import domain.ClickActionInterface;
import domain.Game;
import domain.Mass;
import domain.Player;
import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.ISFINISH;
import domain.value.MASSID;
import domain.value.MASSLIST;
import domain.value.PLAYERID;
import service.bean.ClickResultBean;
//author:ishimori
public class SkillUseService implements ClickActionInterface {
	public SkillUseService()  {
		super();
	}


	public ClickResultBean clickAction(MASSID massID, GAMEID gameID, int turnTotalTime, ClickPlayerID clickPlayerID, Connection con)
			throws SQLException, ClassNotFoundException{
		System.out.println("スキル使うよー");

		PlayerDao pdao = new PlayerDao(con);
		Player player = pdao.getPlayer( clickPlayerID.get() );
		ClickResultBean bean = new ClickResultBean();
		//クールタイムが０以下のときのみ発動可能
		if( player.getCOOLTIME().get() > 0 ){
			System.out.println("まだ使用できぬ･･･！");
			bean.setISFINISH(new ISFINISH(false));
			return bean;
		}
		CharacterSkillEnum SkillEnum = CharacterSkillEnum.getSkillEnumByValue(player.getCHARACTERID().get());
		//スキルの効果が発動
		bean = SkillEnum.useSkill(gameID, clickPlayerID, con);
		//クールタイムをセット
		SkillEnum.setCoolTime(clickPlayerID, con);
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
		bean.setMASSLIST(new MASSLIST(new ArrayList<Mass>()));

		System.out.println(SkillEnum.getSkillName());
		bean.setISFINISH(new ISFINISH(false));

		return  bean;
	}
}
