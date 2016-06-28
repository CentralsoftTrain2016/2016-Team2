package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.value.DIFFICULTYID;
import service.bean.ConfigBean;

/**
 * Servlet implementation class DifficultyChoiceServlet
 */
@WebServlet("/SingleDifficultyChoiceServlet")
public class SingleDifficultyChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingleDifficultyChoiceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String difficulty = request.getParameter("difficulty");
		//文字化けのおまじない
		difficulty =new String(difficulty.getBytes("ISO8859-1"),"UTF-8");
		// 【MatchingServiceで処理】

		ConfigBean config = new ConfigBean();
		DIFFICULTYID defficultyID;

		if (difficulty.equals("初　級")) {
			defficultyID  = new DIFFICULTYID(1);
			config.setDifficultyID(defficultyID);
		} else if (difficulty.equals("上　級")) {
			defficultyID  = new DIFFICULTYID(2);
			config.setDifficultyID(defficultyID);
		}

		System.out.println(config.getDifficultyID().get());
		request.getSession().setAttribute("config", config);
		RequestDispatcher disp = request.getRequestDispatcher("SingleBattleServlet");
		disp.forward(request, response);
	}

}
