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

	// ゲームのプレイヤー1IDとプレイヤー2IDを取得し,セットする。
	public ResultBean getTwoName(int gameid ,int myid) throws SQLException {
		ResultBean rb = new ResultBean();
		GameDao gamedao = new GameDao(con);
		Game game = gamedao.getGame(gameid);

		// PLAYER1IDが自分のIDならば
		// PLAYER1IDから名前を取得し、自分の名前でセットする。
		// PLAYER2IDから名前を取得し、相手の名前でセットする。
		if(game.getPLAYER1ID().get() == myid){
			String myname = gamedao.getPlayer1Name(gameid);
			rb.setMyName(new USERNAME(myname));
			String enemyname = gamedao.getPlayer2Name(gameid);
			rb.setEnemyName(new USERNAME(enemyname));
		}

		// PLAYER2IDが自分のIDならば
		// PLAYER2IDから名前を取得し、自分の名前でセットする。
		// PLAYER1IDから名前を取得し、相手の名前でセットする。
		else if(game.getPLAYER2ID().get() == myid){
			String myname = gamedao.getPlayer2Name(gameid);
			rb.setMyName(new USERNAME(myname));
			String enemyname = gamedao.getPlayer1Name(gameid);
			rb.setEnemyName(new USERNAME(enemyname));
		}

		// それ以外なら該当しない
		else{
			System.out.println("名前を取得できませんでした。");
			rb.setMyName(new USERNAME("エラー"));
			rb.setEnemyName(new USERNAME("エラー"));
		}

		return rb;

	}

	// PLAYER1IDのユーザネームを取得し、セットする。
	public ResultBean getPlayer1Name(int gameid) throws SQLException {
		ResultBean rb = new ResultBean();
		GameDao gamedao = new GameDao(con);

		// PLAYER1IDのユーザネームを取得
		String myname = gamedao.getPlayer1Name(gameid);
		rb.setMyName(new USERNAME(myname));

		return rb;

	}

	public Game gameinfo(int gameid){
		GameDao gamedao = new GameDao(con);
		Game game = new Game();
		try {
			game = gamedao.getGame(gameid);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return game;
	}


//	public static void main(String[] args) throws ClassNotFoundException, SQLException{
//		BattleResultService brs  = new BattleResultService();
//		Game game = brs.gameinfo(1);
//		System.out.println(game.getFINALWINPLAYERID().get());
//
//
//		ResultBean rb = new ResultBean();
//		rb = brs.getTwoName(2, 50);
//		System.out.println(rb.getEnemyName());
//		System.out.println(rb.getMyName());


//		List<Integer>minelist = new ArrayList<Integer>();
//		minelist = fs.minePlacement(1, 256);
//		System.out.println(minelist);
//	}
}
