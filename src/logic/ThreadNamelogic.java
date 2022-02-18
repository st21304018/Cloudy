package logic;

import bean.SelectBean;
import sql.Username;

public class ThreadNamelogic {
	public String nameLogic(SelectBean sb) {
		String sql = "select user_name from cloudy_user where user_id='"+sb.getUserid()+"'";
		Username un = new Username();
		String name = un.username(sql);

		return name;
	}
}
