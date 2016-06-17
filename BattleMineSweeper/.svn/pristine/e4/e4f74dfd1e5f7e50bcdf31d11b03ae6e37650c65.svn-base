package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FieldService;

/**
 * Servlet implementation class BattleServlet
 */
@WebServlet("/BattleServlet")
public class BattleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BattleServlet() {
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

		//【FieldServiceで処理】
		//FieldService fs = new FieldService();

		// 今は固定値を入れている。
		// ゲームIDと難易度IDを取得して入れてください
	    //fs.fieldPlacement(2, 1);

		System.out.println((int)request.getSession().getAttribute("PLAYERID") );

		RequestDispatcher disp = request.getRequestDispatcher("/battle.jsp");
		disp.forward(request, response);

		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
