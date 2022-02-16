import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Board;
import bean.UserBean;

@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リスナークラスに移動したい
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserBean ub = (UserBean) session.getAttribute("account");//beanを入手

		// 既存のコメントを確認
		MyCommentLogic fmcl = new MyCommentLogic();
		Map<Integer, Board> map = fmcl.executeFindMyComment(ub);

		// セッションスコープにコメントリストを保存
		session.setAttribute("map", map);

		RequestDispatcher rd = request.getRequestDispatcher("mypage");
		rd.forward(request, response);
	}
}
