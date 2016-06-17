package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewUserRegistrationServlet
 */
@WebServlet("/NewUserRegistrationServlet")
public class NewUserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserRegistrationServlet() {
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
		// JSPからID(DBではUSERNAMEとなる)とパスワードを取得する
		String userID = request.getParameter("userName");
		String password = request.getParameter("password");

		// 名前の入力がない場合は、同じ画面に戻る
		if (userID == null || userID.equals("")) {
			String message1 = "希望するIDを入力してください";
			request.setAttribute("message1", message1);
			RequestDispatcher disp = request.getRequestDispatcher("/newuser.jsp");
			disp.forward(request, response);
			return;
		} else if (password == null || password.equals("")) {
			String message2 = "希望するPASSWORDを入力してください";
			request.setAttribute("message2", message2);
			RequestDispatcher disp = request.getRequestDispatcher("/newuser.jsp");
			disp.forward(request, response);
			return;
		}

		// 文字化けのおまじない
		userID = new String(userID.getBytes("ISO8859-1"), "UTF-8");
		password = new String(password.getBytes("ISO8859-1"), "UTF-8");

		// ユーザIDが半角英数字・ハイフン・アンダーバーのみで構成されているか調べる
		// 他の文字が含まれている場合は、同じ画面に戻る
		if (!userID.matches("[0-9a-zA-Z-_]+")) {
			String message1 = "ユーザIDは半角英数字・ハイフン・アンダーバーのみ利用できます";
			request.setAttribute("message1", message1);
			RequestDispatcher disp = request.getRequestDispatcher("/newuser.jsp");
			disp.forward(request, response);
			return;
		}

		// //データベースへ接続する
		// LoginService con = new LoginService();

		// // 入力されたIDと同じものが既にデータベースに登録されていないか調べる
		// Users user = con.userLogin(kariUserID);

		// // 既に同じIDのユーザが存在する場合は、同じ画面に戻る
		// if (user != null) {
		// String message1 = "既に利用されているIDです";
		// request.setAttribute("message1", message1);
		// RequestDispatcher disp =
		// request.getRequestDispatcher("/Newcomer.jsp");
		// disp.forward(request, response);
		// return;
		// }

		// // データベースにユーザの新規登録をする
		// User newuser = con.userRegist(userID, userName);

		// // ユーザの情報をセッションに登録する
		// request.getSession().setAttribute("userID",
		// newuser.getUSERID().get());
		// request.getSession().setAttribute("userName",
		// newuser.getUSERNAME().get());
		// request.getSession().setAttribute("winCount",
		// newuser.getWIN().get());
		// request.getSession().setAttribute("loseCount",
		// newuser.getLOSE());
		//
		// // ユーザの情報をリクエストに登録する
		// request.setAttribute("userID", newuser.getUSERID().get());
		// request.setAttribute("userName", newuser.getUSERNAME().get());
		// request.setAttribute("winCount", newuser.getWIN().get());
		// request.setAttribute("loseCount", newuser.getLOSE().get());
		RequestDispatcher disp = request.getRequestDispatcher("MyPageServlet");
		disp.forward(request, response);
	}

}
