import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SelectBean;
import bean.UserBean;
import logic.SearchLogic;

public class SearchReplyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserBean ub = (UserBean) session.getAttribute("account");
		String threadid = (String) session.getAttribute("th_id");

		String tag = req.getParameter("tag");

		String sql = "Select reply_text,reply_date,user_id,reply_tag, th_id ,reply_id, reply_likes from cloudy_reply where th_id = '"+threadid+"' and reply_tag  = '#"+ tag +"' order by reply_id desc";

		SearchLogic search = new SearchLogic();
		Map<Integer, SelectBean> map = search.searchReplyLogic(ub, sql);

		session.setAttribute("map", map);
		RequestDispatcher dis = req.getRequestDispatcher("threadpage");
		dis.forward(req, resp);
	}
}