package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Users;
import service.LoginService;
import service.bean.UserBean;

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
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// 名前の入力がない場合は、同じ画面に戻る
		if (userName == null || userName.equals("")) {
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
		userName = new String(userName.getBytes("ISO8859-1"), "UTF-8");
		password = new String(password.getBytes("ISO8859-1"), "UTF-8");

		//パスワードが半角英数字・ハイフン・アンダーバーのみで構成されているか調べる
		//他の文字が含まれている場合は、同じ画面に戻る
        if (!password.matches("[0-9a-zA-Z-_]+")) {
			String message2 = "そのパスワードは無効です。";
			request.setAttribute("message2", message2);
			RequestDispatcher disp = request.getRequestDispatcher("/newuser.jsp");
			disp.forward(request, response);
			return;
        }

		// データベースへ接続し、ユーザの情報を照合してから追加する
		UserBean userB = new UserBean();
		LoginService logS = null;
		Users user = null;

		try {
			logS = new LoginService();
			// USERNAMEとPASSWORDをセットする。
			user = logS.registUser(userName, password);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			logS.end();
		}

		// 既に同じIDのユーザが存在する場合は、同じ画面に戻る
		if (user == null) {
			String message1 = "既に利用されている名前です";
			request.setAttribute("message1", message1);
			RequestDispatcher disp = request.getRequestDispatcher("/newuser.jsp");
			disp.forward(request, response);
			return;
		}

		//ユーザの各情報をBeanに登録する
		userB.setUserID(user.getUSERID());
		userB.setUserName(user.getUSERNAME());
		userB.setWinCount(user.getWINCOUNT());
		userB.setLoseCount(user.getLOSECOUNT());

		// ユーザの各情報を持つBeanをセッションに登録する
		request.getSession().setAttribute("UserBean",userB);


		// ユーザの各情報を持つBeanをリクエストに登録する
		request.setAttribute("UserBean", userB);

		RequestDispatcher disp = request.getRequestDispatcher("MyPageServlet");
		disp.forward(request, response);
	}

}
