package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Game;
import domain.value.GAMEID;
import domain.value.PLAYERID;
import service.BattleResultService;
import service.bean.ResultBean;

/**
 * Servlet implementation class BattleServlet
 */
@WebServlet("/BattleResultServlet")
public class BattleResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BattleResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// セッションからプレイヤーIDとゲームIDを取得
		// キー名不明のため今は固定値

		//PLAYERID playerid = (PLAYERID) request.getSession().getAttribute("プレイヤーIDのKey");
		PLAYERID playerid = new PLAYERID(1);

		//GAMEID playerid = (GAMEID) request.getSession().getAttribute("ゲームIDのKey");
		GAMEID gameid = new GAMEID(1);

		//受け渡すBeanを作成して、自分と相手のusernameをセットする。
		//残りのデータは次のサーブレットでセットする。
		ResultBean rb = new ResultBean();
		BattleResultService brs = null;
		Game game = new Game();
		try {
			brs = new BattleResultService();
			// ゲームIDをセットする。
			rb = brs.getTwoName(gameid.get());
			game = brs.gameinfo(gameid.get());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally{
			brs.end();
		}

		request.setAttribute("result",rb);


		//if (true) {
		if (playerid.get() == game.getFINALWINPLAYERID().get() ) {
			// 勝側のサーブレットに遷移
			RequestDispatcher disp = request.getRequestDispatcher("/WinnerResultServlet");
			disp.forward(request, response);
		 	}

		else  {
			// 負側のサーブレットに遷移
			RequestDispatcher disp = request.getRequestDispatcher("/loserResult.jsp");
			//RequestDispatcher disp = request.getRequestDispatcher("/LoserResultServlet");
			disp.forward(request, response);
		 }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションからプレイヤーIDとゲームIDを取得
		// キー名不明のため今は固定値

		//PLAYERID playerid = (PLAYERID) request.getSession().getAttribute("プレイヤーIDのKey");
		PLAYERID playerid = new PLAYERID(1);

		//GAMEID playerid = (GAMEID) request.getSession().getAttribute("ゲームIDのKey");
		GAMEID gameid = new GAMEID(1);

		//受け渡すBeanを作成して、自分と相手のusernameをセットする。
		//残りのデータは次のサーブレットでセットする。
		ResultBean rb = new ResultBean();
		BattleResultService brs = null;
		Game game = new Game();
		try {
			brs = new BattleResultService();
			// ゲームIDをセットする。
			rb = brs.getTwoName(gameid.get());
			game = brs.gameinfo(gameid.get());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		finally{
			brs.end();
		}

		request.setAttribute("result",rb);

System.out.println("daskljdajsl");
		if (true) {
		//if (playerid.get() == game.getFINALWINPLAYERID().get() ) {
			// 勝側のサーブレットに遷移
			RequestDispatcher disp = request.getRequestDispatcher("/WinnerResultServlet");
			disp.forward(request, response);
		 	}

		else  {
			// 負側のサーブレットに遷移
			RequestDispatcher disp = request.getRequestDispatcher("/loserResult.jsp");
			//RequestDispatcher disp = request.getRequestDispatcher("/LoserResultServlet");
			disp.forward(request, response);
		 }


	}

}