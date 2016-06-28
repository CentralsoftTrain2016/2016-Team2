package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.PlayerDao;
import domain.Player;
import service.bean.ConfigBean;
import service.bean.UserBean;

/**
 * プレイヤーを生成するサービスです。
 *
 * @author
 *
 */
public class PlayerCreateService extends Service{

	/**
	 * コンストラクタ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public PlayerCreateService() throws SQLException, ClassNotFoundException{
		super();
	}

	/**
	 * コンストラクタ
	 * @param connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public PlayerCreateService(Connection connection) throws SQLException, ClassNotFoundException {
		super(connection);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * コンストラクタ
	 * @param connectService
	 */
	public PlayerCreateService(Service connectService) {
		super(connectService);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * データベースにプレイヤーを生成します。
	 *
	 * @param UserB ユーザーの設定
	 * @param config 難易度とキャラクターの設定
	 * @return プレイヤー
	 * @throws SQLException
	 */
	public Player createPlayer(UserBean UserB, ConfigBean config) throws SQLException{
		PlayerDao playerD = new PlayerDao(con);
		Player player = playerD.createPlayer(UserB.getUserID(), config.getCharacterID());
		return player;
	}

	/**
	 * データベースにシングルプレイのプレイヤーを生成します。
	 *
	 * @param UserB ユーザの設定
	 * @param config 難易度とキャラクターの設定
	 * @return プレイヤー
	 * @throws SQLException
	 */
	public Player createSinglePlayer(UserBean UserB, ConfigBean config) throws SQLException{
		PlayerDao playerD = new PlayerDao(con);
		Player player = playerD.createSinglePlayer(UserB.getUserID());
		return player;
	}
}
