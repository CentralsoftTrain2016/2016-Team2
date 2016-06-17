package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Mass;
import domain.value.GAMEID;
import domain.value.PLAYERID;
import net.arnx.jsonic.JSON;
import service.ChangeTurnPlayerService;
import service.bean.AutoRequestBean;
import service.bean.MassUpdateBean;

/**
 * Servlet implementation class AutoRequestServlet
 */
@WebServlet("/AutoRequestServlet")
public class AutoRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChangeTurnPlayerService ctps = null;
		AutoRequestBean arb = null;
		try {
			ctps = new ChangeTurnPlayerService();
			//自分の手盤か・ゲームが終了したか・マスの更新があったかを確認する
			arb = ctps.checkTurnPlayerOrGameFinished(new GAMEID(1), new PLAYERID((int)request.getSession().getAttribute("PLAYERID")));


			String isMyTurnJson = (String) request.getParameter("isMyTurn");
			boolean isMyTurn = (boolean)JSON.decode(isMyTurnJson);

			if( (isMyTurn != arb.getIsMyTurn()) || (arb.getIsFinished()) ){
				List<Mass> list = ctps.getAllMass(new GAMEID(1));

				for (Mass ms : list) {
					int massId = ms.getMASSID().get();
					//String url = MassTypeEnum.MASSTYPE0.url;
					String url = ms.getMassState().url;
					MassUpdateBean massUpdateBean = new MassUpdateBean(massId, url);

					// デバック用--------------------------------------------------
					System.out.println(massUpdateBean.getMassNumber());
					System.out.println(massUpdateBean.getUrl());

					arb.addMassUpdateBean(massUpdateBean);
				}

				//ターン交代したかどうか
				arb.setIsChanged(true);
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			ctps.end();
		}


		String autoRequestBeanJson = JSON.encode(arb);

		System.out.println(autoRequestBeanJson);
		PrintWriter out = response.getWriter();
		out.println(autoRequestBeanJson);

		return;
	}

}
