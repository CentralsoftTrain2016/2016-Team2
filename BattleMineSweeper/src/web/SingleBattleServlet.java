package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Player;
import domain.value.GAMEID;
import service.FieldService;
import service.PlayerCreateService;
import service.SingleGameCreateService;
import service.bean.ConfigBean;
import service.bean.UserBean;

/**
 * Servlet implementation class BattleServlet
 */
@WebServlet("/SingleBattleServlet")
public class SingleBattleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleBattleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FieldService service = null;



		/*try{
			service = new FieldService();
			Game game = new Game();
			//service.createGame(game);
			int gameid = game.getGAMEID().get();//ゲームID
			int difficultyid = game.getDIFFICULTYID().get();//難易度ID
			service.fieldPlacement(gameid, difficultyid);
		}catch(Throwable e){
			service.rollebackEnd();
			throw e;
		}finally{
			service.end();
		}*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		UserBean userB = (UserBean) request.getSession().getAttribute("UserBean");
		ConfigBean config = (ConfigBean) request.getSession().getAttribute("config");
		PlayerCreateService PCS = null;
		SingleGameCreateService SGCS = null;//ここシングル用に変える
		Player player = null;
		try {
			//プレイヤーをDB登録
			PCS = new PlayerCreateService();
			// UserBeanとConfigBeanをセットする。
			player = PCS.createSinglePlayer(userB, config);
			// 登録したプレイヤーをセッションに登録する
			request.getSession().setAttribute("player",player);
			request.getSession().setAttribute("PLAYERID",player.getPLAYERID().get());

			//マッチング
			SGCS = new SingleGameCreateService(PCS);
			GAMEID gameID = SGCS.createGame(player, config);
			request.getSession().setAttribute("GAMEID", gameID.get());
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			PCS.end();
			if(SGCS != null)SGCS.end();
		}


		RequestDispatcher disp = request.getRequestDispatcher("/singleMode.jsp");
		disp.forward(request, response);

		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
