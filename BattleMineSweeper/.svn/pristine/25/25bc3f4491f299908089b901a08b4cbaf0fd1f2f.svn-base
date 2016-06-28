package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.GameDao;
import domain.Player;
import domain.value.GAMEID;
import domain.value.PLAYMODE;
import service.bean.ConfigBean;

public class SingleGameCreateService extends Service{

	/**
	 * コンストラクタ
	 */
	public SingleGameCreateService() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * コンストラクタ
	 *
	 * @param connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public SingleGameCreateService(Connection connection) throws SQLException, ClassNotFoundException {
		super(connection);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * コンストラクタ
	 *
	 * @param connectService
	 */
	public SingleGameCreateService(Service connectService) {
		super(connectService);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * ゲームを生成し、そのIDを取得します。
	 *
	 * @param player プレイヤー
	 * @param cbean 難易度とキャラクターの設定
	 * @return 生成したゲームID
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public GAMEID createGame(Player player, ConfigBean cbean) throws SQLException, ClassNotFoundException{
		PLAYMODE playmode = PLAYMODE.SINGLE_MODE;


		GameDao gdao = new GameDao(con);

		//ゲーム作成
		GAMEID gameID = gdao.createSingleGame(player, cbean.getDifficultyID(), playmode);

		//フィールド生成
		FieldService fs = new FieldService(con);
		fs.fieldPlacement(gameID.get(), cbean.getDifficultyID().get(), playmode);

		return gameID;
	}
}
