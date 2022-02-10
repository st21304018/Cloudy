import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import sql.InsertSQL;

public class LikeServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();//sessionを入手
        UserBean ub = (UserBean) session.getAttribute("account");//beanを入手

        System.out.println("接続");
        String userID = ub.getUserId();
		String threadID = req.getParameter("threadID");
		String check = req.getParameter("check");
		check = null;

		/*//UserCheckをインスタンス化してidをチェックするためのsql文を渡す
		String checkSql = "select count(user_id) as count from cloudy_like where user_id='"+userID+"', th_id='"+ threadID +"'";
		UserCheck user = new UserCheck();
		String check = user.userCheck(checkSql);
		int n = Integer.parseInt(check);*/
		InsertSQL insert = new InsertSQL();
		if(check == null) {
			System.out.println("いいねされていない");
			String insertSql = "insert into cloudy_like values('"+ userID +"', '"+ threadID +"')";
			insert.insertSQL(insertSql);
			check = "1";
		}else {
			System.out.println("いいねされている");
			String deleteSql = "delete from cloudy_like where user_id = '"+ userID +"'";
			insert.insertSQL(deleteSql);
			check = null;
		}

		req.setAttribute("check", check);

		RequestDispatcher dispatcher=
				req.getRequestDispatcher("mainpage");

			//転送先に要求を転送する
		dispatcher.forward(req,resp);
	}
}
