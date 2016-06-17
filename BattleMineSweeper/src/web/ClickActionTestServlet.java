package web;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MassTypeEnum;
import domain.value.MASSID;
import net.arnx.jsonic.JSON;
import service.bean.BattleClickBean;
import service.bean.MassStateBean;
import service.bean.MassUpdateBean;

//author : Sasaki

/**
 * Servlet implementation class RevueAjaxServlet
 */
@WebServlet("/ClickActionTestServlet")
public class ClickActionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClickActionTestServlet() {
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
		//文字化けのおまじない
		response.setContentType("application/json; charset=UTF-8");

		String battleClickJson = (String)request.getParameter("battleClickBean");
		System.out.println(battleClickJson);

		BattleClickBean battleClickBean = JSON.decode(battleClickJson,BattleClickBean.class);

		MASSID massID = new MASSID(battleClickBean.getClickObjectNumber());





		//JSON変換
		//String massClickResultBean = JSON.encode(colorBean);
		//String massClickResultBean = "{\"num\":" + massID.get() + ",\"url\":\"masImg/masType2.png\"}";
		//JSON形式の表示
		//System.out.println(massClickResultBean);

		MassStateBean massStateBean = new MassStateBean();
		for (int i=0;i<2;i++) {
			int massId = massID.get()+1-2*i;
			String url = MassTypeEnum.MASSTYPE0.url;
			MassUpdateBean massUpdateBean = new MassUpdateBean(massId, url);

			// デバック用--------------------------------------------------
			System.out.println(massUpdateBean.getMassNumber());
			System.out.println(massUpdateBean.getUrl());

			massStateBean.addMassUpdateBean(massUpdateBean);
		}

		String massClickBean = JSON.encode(massStateBean);

		System.out.println(massClickBean);
		PrintWriter out = response.getWriter();
		out.println(massClickBean);

		return;

		//値を返す
		//PrintWriter out = response.getWriter();
		//out.println(massClickResultBean);

		//return;
	}

}
