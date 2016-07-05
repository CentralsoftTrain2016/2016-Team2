package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GameDao;
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

	public CountZeroService(Connection connection){
		super(connection);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public CountZeroService(Service connectService){
		super(connectService);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//カウントが0になったら負け
	//全マスをオープンして返す
	public List<Mass> setToLoseForCountZero(GAMEID gameID, PLAYERID playerID){
		try {
			WinorLoseEnum.LOSE.updateResult(gameID, playerID, this.con);

			MassDao mdao = new MassDao(this.con);
			mdao.updateAllISOPEN(gameID.get());

			return mdao.getAllMass(gameID.get());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//相手のカウントが0になったと見なす秒数
	private final int COUNTMAX = 30;

	//相手のカウントが0を下回っても勝敗がついていなかったら勝ち
	public void setToWinForCountZero(GAMEID gameID, PLAYERID playerID){
		GameDao gdao = new GameDao(this.con);
		String dateStr;
		try {
			dateStr = gdao.getElapsedTime(gameID);

			//nullならreturn
			if(dateStr == null) return;

			String secondsStr = dateStr.substring(dateStr.length()-12, dateStr.length()-10);
			String minutesStr = dateStr.substring(dateStr.length()-15, dateStr.length()-13);
			int elapsedTime = Integer.parseInt(minutesStr) * 60 + Integer.parseInt(secondsStr);

			if(elapsedTime >= COUNTMAX)WinorLoseEnum.WIN.updateResult(gameID, playerID, this.con);

			return;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
