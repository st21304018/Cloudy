import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ThreadPageServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		String sql=" insert into cloudy_reply (reply_text, reply_tag) valuse(?,?)";

		String text = req.getParameter("reply_text");
		String tag = req.getParameter("reply_tag");

		req.setAttribute("text",text);
		req.setAttribute("tag",tag);

		RequestDispatcher dispatcher=
				req.getRequestDispatcher("threadpage");

		dispatcher.forward(req, res);
	}
}
