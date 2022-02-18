package logic;

import java.util.Map;

import bean.Board;
import bean.UserBean;
import main.FindCommentSQL;

public class SearchLogic {
	public Map<Integer, Board> searchLogic(UserBean ubean, String sql) {

       FindCommentSQL fcdao = new FindCommentSQL();
       Map<Integer, Board> map = fcdao.findcomment(ubean, sql);

       return map;
   }
}
