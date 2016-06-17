package service;

import java.sql.Connection;
import java.sql.SQLException;

import domain.ClickActionInterface;
import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.MASSID;
import service.bean.ClickResultBean;
//author:ishimori
public class SkillUseService implements ClickActionInterface {
	public SkillUseService()  {
		super();
	}
	public ClickResultBean clickAction(MASSID massID, GAMEID gameID, ClickPlayerID clickPlayerID, Connection con)
			throws SQLException, ClassNotFoundException{
		System.out.println("スキル使うよー");
		ClickResultBean bean = null;
		return  bean;
	}
}
