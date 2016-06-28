package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.GameDao;
import domain.Player;
import domain.value.GAMEID;
import domain.value.PLAYMODE;
import service.bean.ConfigBean;

public class MatchingDecisionService extends Service{

	/**
	 * コンストラクタ
	 */
	public MatchingDecisionService() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * コンストラクタ
	 * @param connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public MatchingDecisionService(Connection connection) throws SQLException, ClassNotFoundException {
		super(connection);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * コンストラクタ
	 * @param connectService
	 */
	public MatchingDecisionService(Service connectService) {
		super(connectService);
		// TODO 自動生成されたコンストラクター・スタブ
	}


	/**
	 *
	 *
	 * @param player
	 * @param cbean
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public GAMEID matchingDecision(Player player, ConfigBean cbean) throws SQLException, ClassNotFoundException{
		PLAYMODE playmode = PLAYMODE.BATTLE_MODE;

		//ゲームがあるか確認する
		GameDao gdao = new GameDao(con);
		GAMEID gameID = gdao.getMatchGameID(player, cbean);

		//マッチング成功
		if(gameID != null){
			return gameID;
		}

		//マッチングしなかったとき
		//ゲーム作成
		gameID = gdao.createGame(player, cbean.getDifficultyID(), playmode);

		//フィールド生成
		FieldService fs = new FieldService(con);
		fs.fieldPlacement(gameID.get(), cbean.getDifficultyID().get(), playmode);

		return gameID;
	}
}
