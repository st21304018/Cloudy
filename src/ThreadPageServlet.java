import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SelectBean;
import bean.textBean;
import sql.ReplySelectSql;
import sql.ThreadInsertSql;
import sql.ThreadSelectSql;

public class ThreadPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		SelectBean sb = new SelectBean();

		String threadid = req.getParameter("e");

		String sql = "select th_text,th_date,th_tag,user_id from cloudy_thread where th_id = '" + threadid + "'";

		ThreadSelectSql tss = new ThreadSelectSql();
		sb = tss.ThreadText(sql);

		String text = sb.getText();
		String date = sb.getTime();
		String tag = sb.getTag();
		String name = sb.getUsername();

		Map<Integer, SelectBean> map = new LinkedHashMap<Integer, SelectBean>();
		ReplySelectSql rs = new ReplySelectSql();

		String replysql = "Select reply_text,reply_date,user_id,reply_tag,th_id "
				+ "from cloudy_reply where th_id = '" + threadid + "'";

		map = rs.replySelect(replysql);

		HttpSession session = req.getSession(); //sessionを入手
		session.setAttribute("th_id", threadid);
		session.setAttribute("threadtext", text);
		session.setAttribute("date", date);
		session.setAttribute("th_tag", tag);
		session.setAttribute("user_name", name);

		req.setAttribute("map", map);

		RequestDispatcher dispatcher = req.getRequestDispatcher("threadpage");

		dispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();//sessionを入手
		String threadid = (String) session.getAttribute("th_id");//beanを入手
		String userid = (String) session.getAttribute("user_id");

		String text = req.getParameter("text");
		String tag = req.getParameter("tag");

		textBean bean = new textBean();
		bean.setText(text);
		bean.setTag(tag);

		String sql = " insert into cloudy_reply(reply_id,reply_text,reply_tag,th_id,user_name) values(reply_seq.nextval,?,?,"
				+ threadid + ")";

		ThreadInsertSql in = new ThreadInsertSql();
		in.setText(bean, sql);

		Map<Integer, SelectBean> map = new LinkedHashMap<Integer, SelectBean>();
		ReplySelectSql rs = new ReplySelectSql();

		String replysql = "Select reply_text,reply_date,user_id,reply_tag,reply_date,th_id,user_name,user_id from cloudy_reply where th_id = '"
				+ threadid + "'";
		String usersql = "select user_name from cloudy_user where th_id = '"+threadid+"'";
		map = rs.replySelect(replysql);

		req.setAttribute("map", map);

		RequestDispatcher dispatcher = req.getRequestDispatcher("threadpage");

		dispatcher.forward(req, res);
	}
}
