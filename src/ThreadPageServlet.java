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
import bean.UserBean;
import bean.textBean;
import logic.NewLineLogic;
import sql.ReplySelectSql;
import sql.ThreadInsertSql;
import sql.ThreadSelectSql;

public class ThreadPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		SelectBean sb = new SelectBean();


		String threadid = req.getParameter("e");

		HttpSession session = req.getSession();
		UserBean ub = (UserBean) session.getAttribute("account");//beanを入手

		String sql = "select th_text,th_date,th_tag,user_id from cloudy_thread where th_id = '" + threadid + "'";

		ThreadSelectSql tss = new ThreadSelectSql();
		sb = tss.ThreadText(sql, ub);

		session.setAttribute("sb", sb);

		Map<Integer, SelectBean> map = new LinkedHashMap<Integer, SelectBean>();
		ReplySelectSql rs = new ReplySelectSql();

		String replysql = "Select reply_text,reply_date,user_id,reply_tag,th_id,reply_id, reply_likes "
				+ "from cloudy_reply where th_id = '" + threadid + "' order by reply_id desc";

		map = rs.replySelect(replysql, ub);


		session.setAttribute("th_id", threadid);


		session.setAttribute("map", map);

		RequestDispatcher dispatcher = req.getRequestDispatcher("threadpage");

		dispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();//sessionを入手
		String threadid = (String) session.getAttribute("th_id");//beanを入手
		UserBean ub = (UserBean) session.getAttribute("account");

		String text = req.getParameter("text");
		String tag = req.getParameter("tag");
		if(tag.isEmpty()) {
        }else {
        	tag = "#" + tag;
        }

		 String textNeo = NewLineLogic.htmlEscape(text);
	     String textNewline = textNeo.replaceAll("[\n]", "<br>");

		textBean bean = new textBean();
		bean.setText(textNewline);
		bean.setTag(tag);

		String sql = " insert into cloudy_reply(reply_id,reply_text,reply_tag,th_id,user_id) values(reply_seq.nextval,?,?,"
				+ threadid + ",'"+ub.getUserId()+"')";

		ThreadInsertSql in = new ThreadInsertSql();
		in.setText(bean, sql);

		Map<Integer, SelectBean> map = new LinkedHashMap<Integer, SelectBean>();
		ReplySelectSql rs = new ReplySelectSql();


		String replysql = "Select reply_text,reply_date,user_id,reply_tag, th_id ,reply_id, reply_likes from cloudy_reply where th_id = '"
				+ threadid + "' order by reply_id desc";

		map = rs.replySelect(replysql, ub);

		session.setAttribute("map", map);

		RequestDispatcher dispatcher = req.getRequestDispatcher("threadpage");

		dispatcher.forward(req, res);
	}
}
