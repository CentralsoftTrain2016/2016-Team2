package service;

import java.sql.SQLException;

import dao.GameDao;
import domain.Game;
import domain.value.USERNAME;
import service.bean.ResultBean;

public class BattleResultService extends Service {
	public BattleResultService() throws SQLException, ClassNotFoundException{
		super();
	}

	public ResultBean getTwoName(int gameid ) {
		ResultBean rb = new ResultBean();
		//GameDao gamedao = new GameDao(con);
		//Game game = gamedao.getGame(gameid);
		rb.setMyName(new USERNAME("aaaaaaaaa"));
		rb.setEnemyName(new USERNAME("bbbbbbb"));
		return rb;

	}

	public Game gameinfo(int gameid) throws SQLException {
		GameDao gamedao = new GameDao(con);
		Game game = new Game();
		game = gamedao.getGame(gameid);
		return game;
	}
}
