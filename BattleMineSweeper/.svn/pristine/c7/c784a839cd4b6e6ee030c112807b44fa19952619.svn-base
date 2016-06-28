package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.value.PLAYERID;
import domain.value.TOTALTIME;
import service.QuizIncorrectService;

/**
 * Servlet implementation class QuizIncorrectServlet
 */
@WebServlet("/QuizIncorrectServlet")
public class QuizIncorrectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizIncorrectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuizIncorrectService qis = null;
		new TOTALTIME(60000);
		new PLAYERID((int)request.getSession().getAttribute("PLAYERID"));

		try {
			qis = new QuizIncorrectService();
			qis.addTotalTime(
					new TOTALTIME(60000),
					new PLAYERID((int)request.getSession().getAttribute("PLAYERID"))
					);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch(Throwable e){
			e.printStackTrace();
		} finally{
			qis.end();
		}

		return;
	}

}
