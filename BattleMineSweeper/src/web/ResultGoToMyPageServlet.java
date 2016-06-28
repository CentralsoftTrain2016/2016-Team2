package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dao.UsersDao;
import domain.Users;
import domain.value.USERNAME;
import service.bean.UserBean;

/**
 * Servlet implementation class ResultGoToMyPageServlet
 */
@WebServlet("/ResultGoToMyPageServlet")
public class ResultGoToMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultGoToMyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con  = null;
		// ユーザの各情報を持つBeanをセッションから呼び出す
		try{
			UserBean ubean = (UserBean)request.getSession().getAttribute("UserBean");
			con = Dao.getConnection();
			UsersDao udao = new UsersDao(con);
			Users users = udao.checkUser(ubean.getUserName().get());
			ubean.setUserID(users.getUSERID());
			ubean.setUserName(users.getUSERNAME());
			ubean.setWinCount(users.getWINCOUNT());
			ubean.setLoseCount(users.getLOSECOUNT());
			// 文字化けのおまじない
			ubean.setUserName(  new USERNAME (  new String(   ubean.getUserName().get().getBytes("ISO8859-1")  ,"UTF-8")  ) );

			//情報をリクエストに登録
			request.setAttribute("UserBean", ubean);

			RequestDispatcher disp = request.getRequestDispatcher("MyPageServlet");
			disp.forward(request, response);
		}catch(SQLException | ClassNotFoundException e){
		//}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(con != null){
					con.rollback();
					con.close();
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
