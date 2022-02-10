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
	private static final long serialVersionUID = 1L;

	public AccountSearchServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

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
			HttpSession session = request.getSession();
			session.setAttribute("account", returnUb);

			RequestDispatcher rd = request.getRequestDispatcher("LoginSuccess.jsp");
			rd.forward(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("LoginError.jsp");
			rd.forward(request, response);
		}
	}
}