package main;

import java.util.Map;

import bean.Board;
import bean.UserBean;

public class FindCommentLogic {
    public Map<Integer, Board> executeFindComment(UserBean ubean) {
    	 String sql = "select th_id, th_text, th_likes, user_id, th_date, th_tag from cloudy_thread order by th_id desc";
        FindCommentSQL fcdao = new FindCommentSQL();
        Map<Integer, Board> map = fcdao.findcomment(ubean, sql);

        return map;
    }

}