package logic;

import bean.Board;
import bean.SelectBean;
import bean.UserBean;
import sql.UserCheck;

public class LikeCheckLogic {
	public String likeLogic(Board bean, UserBean ubean) {
		String sql = "select count(*) from cloudy_like where user_id='"+ubean.getUserId()+"' and th_id="+ bean.getId()+"";
		UserCheck check = new UserCheck();
		String ch = check.userCheck(sql);

		return ch;
	}
	public String likeRepLogic(SelectBean bean, UserBean ubean) {
		String sql = "select count(*) from cloudy_rep_like where user_id='"+ubean.getUserId()+"' and reply_id="+ bean.getReply_id()+"";
		UserCheck check = new UserCheck();
		String ch = check.userCheck(sql);

		return ch;
	}
}
