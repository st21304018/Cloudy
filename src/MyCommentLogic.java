import java.util.Map;

import bean.Board;
import bean.UserBean;
import sql.MyCommentSQL;

public class MyCommentLogic {

	public Map<Integer, Board> executeFindMyComment(UserBean ubean) {
		MyCommentSQL mcdao = new MyCommentSQL();
		Map<Integer, Board> map = mcdao.findcomment(ubean);
		return map;
	}

}
