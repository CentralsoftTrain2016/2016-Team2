package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.bean.ResultBean;

/**
 * Servlet implementation class LosePlayerServlet
 */
@WebServlet("/SingleLoserResultServlet")
public class SingleLoserResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingleLoserResultServlet() {
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
		/*ResultBean wrb = (ResultBean) request.getAttribute("result");

		// サービスをnew
		LoserResultAdDescriptService lRADS = null;
		try {
			lRADS = new LoserResultAdDescriptService();
			Ad ado = lRADS.getAdDescript();// WinnerResultInfoDescriptServiceのgetInfoDescript呼び出し

			ADDESCRIPTION adDescript_p = ado.getADDESCRIPTION();// DictionaryItemDao
			System.out.println(adDescript_p.get());
			wrb.setAdDescription(adDescript_p);// ResultBeanに図鑑を入れる

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			lRADS.end();
		}

		// UserBean userBean = new UserBean();
		// ResultBean resultBean = new ResultBean();
		// wrb.setMyName(userBean.getUserName());
		// 【地雷の知識を選ぶ処理がこのあたり？】
		// resultBean.setInfoDescription(【地雷についての説明がはいる？】);
		request.setAttribute("bean", wrb);
		*/
		ResultBean rb = (ResultBean) request.getAttribute("result");
		request.setAttribute("result", rb);
		RequestDispatcher disp = request.getRequestDispatcher("/singleLoserResult.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
