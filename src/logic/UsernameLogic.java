package logic;

import bean.UserBean;
import main.Board;
import sql.UserCheck;

public class UsernameLogic {
	public String NameLogic(Board bean, UserBean ubean) {
		String sql = "select count(*) from cloudy_like where user_id='"+ubean.getUserId()+"' and th_id="+ bean.getId()+"";
		UserCheck check = new UserCheck();
		String ch = check.userCheck(sql);

		return ch;
	}
}
