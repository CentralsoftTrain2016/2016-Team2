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
import service.CountZeroService;
import service.bean.CountZeroBean;
import service.bean.MassUpdateBean;

/**
 * Servlet implementation class CountZeroServlet
 */
@WebServlet("/CountZeroServlet")
public class CountZeroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountZeroServlet() {
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
		CountZeroService countZeroService = null;
		CountZeroBean czbean = new CountZeroBean();

		try {
			countZeroService = new CountZeroService();
			List<Mass> list = countZeroService.setToLoseForCountZero(new GAMEID(1), new PLAYERID((int)request.getSession().getAttribute("PLAYERID")));

			for (Mass ms : list) {
				int massId = ms.getMASSID().get();
				//String url = MassTypeEnum.MASSTYPE0.url;
				String url = ms.getMassState().url;
				MassUpdateBean massUpdateBean = new MassUpdateBean(massId, url);

				// デバック用--------------------------------------------------
				System.out.println(massUpdateBean.getMassNumber());
				System.out.println(massUpdateBean.getUrl());

				czbean.addMassUpdateBean(massUpdateBean);
				System.out.println("adasfsaf");
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally{
			countZeroService.end();
		}


		String countZeroBeanJson = JSON.encode(czbean);

		System.out.println(countZeroBeanJson);
		PrintWriter out = response.getWriter();
		out.println(countZeroBeanJson);

		return;
	}

}
