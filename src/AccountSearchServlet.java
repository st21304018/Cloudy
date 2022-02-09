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

/**
 * Servlet implementation class AccountDAO2
 */
@WebServlet("/AccountSearchServlet")
public class AccountSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String userId = request.getParameter("userId");
        String pass = request.getParameter("pass");

        // login.jspから受け取ったログインIDとpassをビーンズにセット
        UserBean ub = new UserBean();
        ub.setUserId(userId);
        ub.setPass(pass);

        // アカウントの有無を検索
        // 検索したアカウント情報を取得
        LoginDB ld = new LoginDB();
        UserBean returnUb = ld.findAccount(ub);

        if(returnUb != null) {
            // セッションにアカウント情報＆ロールを登録
            HttpSession session = request.getSession();
            session.setAttribute("account", returnUb);

            RequestDispatcher rd = request.getRequestDispatcher("LoginSuccess.jsp");
            rd.forward(request, response);

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request, response);
        }
    }
}