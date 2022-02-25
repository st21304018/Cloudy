import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Board;
import bean.UserBean;
import logic.SearchLogic;

public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserBean ub = (UserBean) session.getAttribute("account");


		String tag = req.getParameter("tag");

		String sql = "select th_id, th_text, th_likes, user_id,th_date,th_tag from cloudy_thread where th_tag = '#"+ tag +"' order by th_id desc";

		SearchLogic search = new SearchLogic();
		Map<Integer, Board> map = search.searchLogic(ub, sql);

		session.setAttribute("map", map);
		RequestDispatcher dis = req.getRequestDispatcher("mainpage");
		dis.forward(req, resp);
	}
}
