package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.bean.ResultBean;
import service.bean.UserBean;

/**
 * Servlet implementation class LosePlayerServlet
 */
@WebServlet("/LoserResultServlet")
public class LoserResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoserResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		UserBean userBean = new UserBean();
		ResultBean resultBean = new ResultBean();
		resultBean.setMyName(userBean.getUserName());
		//resultBean.setEnemyName(【対戦相手のUSERNAMEがほしい】);
		//【広告を選ぶ処理がこのあたり？】
		//resultBean.setAdDescription(【広告の内容がはいる？】);
		request.setAttribute("bean", resultBean);
		RequestDispatcher disp = request.getRequestDispatcher("/loserResult.jsp");
		disp.forward(request, response);
	}

}
