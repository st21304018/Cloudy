import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Board;
import bean.UserBean;
import logic.DecrementLogic;
import logic.IncrementLogic;
import sql.InsertSQL;
import sql.SelectSQL;

public class LikeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		LinkedHashMap<Integer, Board> map = (LinkedHashMap<Integer, Board>)session.getAttribute("map");
		int threadId = Integer.parseInt(req.getParameter("e"));
		UserBean ub = (UserBean) session.getAttribute("account");
		Board bean = map.get(threadId);
		String check = bean.getCheck();
		InsertSQL insert = new InsertSQL();

		if(check == null) {
			System.out.println("いいねされていない");
			String insertSql = "insert into cloudy_like values('"+ ub.getUserId() +"', '"+ bean.getId() +"')";
			insert.insertSQL(insertSql);
			IncrementLogic inc  = new IncrementLogic();
			inc.incrementLogic(bean);
			bean.setCheck("1");
		}else {
			System.out.println("いいねされている");
			String deleteSql = "delete from cloudy_like where user_id = '"+ ub.getUserId() +"'";
			insert.insertSQL(deleteSql);
			DecrementLogic dec = new DecrementLogic();
			dec.decrementLogic(bean);
			bean.setCheck(null);
		}

		String selectsql = "select th_likes from cloudy_thread where th_id="+bean.getId()+"";
		int like = new SelectSQL().selectSQL(selectsql);
		bean.setLikes(like);
		map.put(bean.getId(), bean);

		session.setAttribute("map", map);
		RequestDispatcher dis = req.getRequestDispatcher("mainpage");
		dis.forward(req, resp);
	}

}
