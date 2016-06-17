package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DifficultyChoiceServlet
 */
@WebServlet("/DifficultyChoiceServlet")
public class DifficultyChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DifficultyChoiceServlet() {
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

		if (difficulty.equals("初級")) {
			request.getSession().setAttribute("difficultyID", 1);
		} else if (difficulty.equals("上級")) {
			request.getSession().setAttribute("difficultyID", 2);
		}

		RequestDispatcher disp = request.getRequestDispatcher("CharacterServlet");
		disp.forward(request, response);
	}

}
