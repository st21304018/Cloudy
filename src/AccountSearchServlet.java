import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import sql.LoginDB;

@WebServlet("/AccountSearchServlet")
public class AccountSearchServlet extends HttpServlet {
	public AccountSearchServlet() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String pass = req.getParameter("pass");

		pass = hash.Hashing.hashing(pass);

		// login.jspから受け取ったログインIDとpassをビーンズにセット
		UserBean ub = new UserBean();
		ub.setUserId(userId);
		ub.setPassWord(pass);

		// アカウントの有無を検索
		// 検索したアカウント情報を取得
		LoginDB ld = new LoginDB();
		UserBean returnUb = ld.findAccount(ub);

		if (returnUb != null) {
			// セッションにアカウント情報＆ロールを登録
			HttpSession session = req.getSession();
			session.setAttribute("account", returnUb);

			RequestDispatcher rd = req.getRequestDispatcher("LoginSuccess.jsp");
			rd.forward(req, res);

		} else {
			RequestDispatcher rd = req.getRequestDispatcher("LoginError.jsp");
			rd.forward(req, res);
		}
	}
}