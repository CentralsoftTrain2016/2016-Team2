package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CharacterDao;
import domain.Character;
import service.bean.ConfigBean;

public class CharacterService extends Service {

	public CharacterService() {
		super();
	}

	public CharacterService(Connection connection) throws SQLException, ClassNotFoundException {
		super(connection);
	}

	public CharacterService(Service connectService) {
		super(connectService);
	}

	public Character getCharacterInfomation(ConfigBean config_p) throws SQLException{
		ConfigBean config = config_p;
		CharacterDao charD = new CharacterDao(con);
		Character selectChar = charD.getCharacterInfo(config.getCharacterID().get());
		return selectChar;
	}
}