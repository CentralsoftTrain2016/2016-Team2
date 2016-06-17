package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GuideChoceServlet
 */
@WebServlet("/GuideChoceServlet")
public class GuideChoceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuideChoceServlet() {
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

		String guide = request.getParameter("guide");

		System.out.println(guide);

		if (guide.equals("????")) {
			RequestDispatcher disp = request.getRequestDispatcher("/guide.jsp");
			disp.forward(request, response);
			return;
		}

		//
		// 【GuideServiceの処理】
		// 【GuideBeanの処理】
		//

		RequestDispatcher disp = request.getRequestDispatcher("DetailServlet");
		disp.forward(request, response);
	}

}
