package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.MassDao;
import domain.Mass;
import domain.WinorLoseEnum;
import domain.value.GAMEID;
import domain.value.PLAYERID;

public class CountZeroService extends Service {
	//コンストラクタ
	public CountZeroService() throws SQLException, ClassNotFoundException{
		super();
	}

	public CountZeroService(Connection connection) throws SQLException, ClassNotFoundException {
		super(connection);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public CountZeroService(Service connectService) throws SQLException, ClassNotFoundException {
		super(connectService);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//カウントが0になったら負け
	//全マスをオープンして返す
	public List<Mass> setToLoseForCountZero(GAMEID gameID, PLAYERID playerID) throws SQLException{
		WinorLoseEnum.LOSE.updateResult(gameID, playerID, this.con);

		MassDao mdao = new MassDao(this.con);
		mdao.updateAllISOPEN(gameID.get());

		return mdao.getAllMass(gameID.get());
	}

	//相手のカウントが0になったと見なす時間
	private final int COUNTMAX = 20;

	//相手のカウントが0を下回っても勝敗がついていなかったら勝ち
	public void setToWinForCountZero(GAMEID gameID, PLAYERID playerID) throws SQLException{
		WinorLoseEnum.WIN.updateResult(gameID, playerID, this.con);

		return;
	}

}
