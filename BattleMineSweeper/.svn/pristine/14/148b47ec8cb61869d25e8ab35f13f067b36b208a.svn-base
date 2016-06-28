package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.PlayerDao;
import domain.Player;
import domain.value.PLAYERID;
import domain.value.TOTALTIME;

public class QuizIncorrectService extends Service{

	public QuizIncorrectService() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public QuizIncorrectService(Connection connection) throws SQLException, ClassNotFoundException {
		super(connection);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public QuizIncorrectService(Service connectService) {
		super(connectService);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Player addTotalTime(TOTALTIME addTime, PLAYERID playerID) throws SQLException{
		PlayerDao pdao = new PlayerDao(con);
		pdao.totalTime(addTime.get(), playerID);
		Player player = pdao.getPlayer(playerID.get());

		return player;
	}

}
