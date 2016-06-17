package domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.MASSID;
import service.ClickActionService;
import service.ItemUseService;
import service.SkillUseService;
import service.bean.ClickResultBean;

public enum ClickObjectTypeEnum {
	MASS( new ClickActionService() ),
	ITEM( new ItemUseService() ),
	SKILL(new SkillUseService()) ;

	private ClickActionInterface cai;


	private ClickObjectTypeEnum(ClickActionInterface ca){
		this.cai = ca;
	}
	public ClickResultBean clickAction(MASSID massID, GAMEID gameID,ClickPlayerID clickPlayerID, Connection con)
			throws SQLException,ClassNotFoundException  {

		return this.cai.clickAction( massID,  gameID, clickPlayerID, con);
	}
//	public void start(Service connectService)
//	{
//		clickAction.start(connectService);
//	}
//
//	public void start(Connection connection)
//	{
//		clickAction.start(connection);
//	}
//
//	public void start()
//	{
//		clickAction.start();
//	}
//	public void end(){
//		this.clickAction.end();
//	}
//	public void rollebackEnd(){
//		this.clickAction.rollebackEnd();
//	}

	//ClickObjectTypeEnumのなかのいろいろ
	private String value;
	private static Map<String, ClickObjectTypeEnum> map = new HashMap<String, ClickObjectTypeEnum>();

	private ClickObjectTypeEnum(String value) {
		this.value = value;
	}

	static{
		map.put("mass", MASS);
		map.put("item", ITEM);
		map.put("skill", SKILL);
	}

	public String get() {
		return value;
	}

	public static ClickObjectTypeEnum getClickObjectTypeByValue(String value){
		return map.get(value);
	}

//	public abstract ClickResultBean objectClickAction(MASSID massID, GAMEID gameID)
//			throws SQLException,ClassNotFoundException ;
//	public abstract void rollebackEnd();
//	public abstract void end();
};






