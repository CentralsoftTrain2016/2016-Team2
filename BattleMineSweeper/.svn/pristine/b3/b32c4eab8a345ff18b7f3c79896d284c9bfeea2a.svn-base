package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginLoginServlet
 */
@WebServlet("/LoginLoginServlet")
public class LoginLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginLoginServlet() {
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
		// ユーザ名(画面ではIDとしている)を取得する
		String userName = request.getParameter("userName");
		// パスワードを取得する
		String password = request.getParameter("password");

		// 名前の入力がない場合は、同じ画面に戻る
		if (userName == null || userName.equals("")) {
			String message1 = "IDが未入力です";

			// メッセージをリクエストに登録
			request.setAttribute("message1", message1);
			RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
			disp.forward(request, response);
			return;
		} else if (password == null || password.equals("")) {
			String message2 = "パスワードが未入力です";

			// メッセージをリクエストに登録
			request.setAttribute("message2", message2);
			RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
			disp.forward(request, response);
			return;
		}

		// 文字化けのおまじない
		userName = new String(userName.getBytes("ISO8859-1"), "UTF-8");

		// //データベースへ接続し、ユーザの情報を照合する
		// LoginService con = new LoginService();
		// Users user = con.userLogin(kariUserID);

		// // ユーザIDが存在しない場合は、元の画面に戻る
		// if (user == null) {
		// String message1 = "ログインできませんでした";
		// request.setAttribute("message1", message1);
		// RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
		// disp.forward(request, response);
		// return;
		// }

		// //ユーザの各情報をセッションに登録する
		request.getSession().setAttribute("userID", 1); //カッコカリ
		// request.getSession().setAttribute("userID", user.getUSERID().get());
		// request.getSession().setAttribute("userName",
		// user.getUSERNAME().get());
		// request.getSession().setAttribute("winCount",
		// user.getWINCOUNT().get());
		// request.getSession().setAttribute("loseCount",
		// user.getLOSECOUNT().get());
		//
		// //ユーザの各情報をリクエストに登録する
		// request.setAttribute("userID", user.getUSERID().get());
		// request.setAttribute("userName", user.getUSERNAME().get());
		// request.setAttribute("winCount", user.getWINCOUNT().get());
		// request.setAttribute("loseCount", user.getLOSECOUNT().get());
		RequestDispatcher disp = request.getRequestDispatcher("MyPageServlet");
		disp.forward(request, response);
	}

}
