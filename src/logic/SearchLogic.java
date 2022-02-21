package logic;

import java.util.Map;

import bean.Board;
import bean.SelectBean;
import bean.UserBean;
import main.FindCommentSQL;
import sql.ReplySelectSql;

public class SearchLogic {
	public Map<Integer, Board> searchLogic(UserBean ubean, String sql) {

       FindCommentSQL fcdao = new FindCommentSQL();
       Map<Integer, Board> map = fcdao.findcomment(ubean, sql);

       return map;
   }
	public Map<Integer, SelectBean> searchReplyLogic(UserBean ubean, String sql) {

       ReplySelectSql fcdao = new ReplySelectSql();
       Map<Integer, SelectBean> map = fcdao.replySelect(sql, ubean);

       return map;
	}
}
