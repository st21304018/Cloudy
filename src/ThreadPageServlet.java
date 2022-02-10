import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.textBean;
import sql.ThreadInsertSql;

public class ThreadPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String s = req.getParameter("");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			
			
		

		String text = req.getParameter("text");
		String tag = req.getParameter("tag");

		textBean bean = new textBean();
		bean.setText(text);
		bean.setTag(tag);



		String sql=" insert into cloudy_reply (reply_text,reply_tag) values(?,?)";

		ThreadInsertSql in = new ThreadInsertSql();
		in.setText(bean,sql);


		req.setAttribute("text",text);
		req.setAttribute("tag",tag);

		RequestDispatcher dispatcher=
				req.getRequestDispatcher("threadpage");

		dispatcher.forward(req, res);
	}
}
