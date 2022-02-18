package logic;

import bean.Board;
import sql.Username;


public class UsernameLogic {
	public String nameLogic(Board bo) {
		String sql = "select user_name from cloudy_user where user_id='"+bo.getUser_id()+"'";
		Username un = new Username();
		String name = un.username(sql);

		return name;
	}

	public String nameMyLogic(Board bo) {
		String sql = "select user_name from cloudy_user where user_id='"+bo.getUser_id()+"'";
		Username un = new Username();
		String name = un.username(sql);

		return name;
	}
}
