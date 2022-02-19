import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SelectBean;
import bean.UserBean;
import logic.DecrementLogic;
import logic.IncrementLogic;
import sql.InsertSQL;
import sql.SelectSQL;

public class LikeRepServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		LinkedHashMap<Integer, SelectBean> map = (LinkedHashMap<Integer, SelectBean>)session.getAttribute("map");
		int replyId = Integer.parseInt(req.getParameter("e"));
		UserBean ub = (UserBean) session.getAttribute("account");
		SelectBean bean = map.get(replyId);
		String check = bean.getCheck();
		InsertSQL insert = new InsertSQL();

		if(check == null) {
			System.out.println("いいねされていない");
			String insertSql = "insert into cloudy_rep_like values('"+ ub.getUserId() +"', '"+ bean.getReply_id() +"')";
			insert.insertSQL(insertSql);
			IncrementLogic inc  = new IncrementLogic();
			inc.incrementRepLogic(bean);
			bean.setCheck("1");
		}else {
			System.out.println("いいねされている");
			String deleteSql = "delete from cloudy_rep_like where user_id = '"+ ub.getUserId() +"'";
			insert.insertSQL(deleteSql);
			DecrementLogic dec = new DecrementLogic();
			dec.decrementRepLogic(bean);
			bean.setCheck(null);
		}

		String selectsql = "select reply_likes from cloudy_reply where reply_id="+bean.getReply_id()+"";
		int like = new SelectSQL().selectSQL(selectsql);
		bean.setLikes(like);
		map.put(bean.getReply_id(), bean);

		session.setAttribute("map", map);
		RequestDispatcher dis = req.getRequestDispatcher("threadpage");
		dis.forward(req, resp);
	}
}
