package main;

import java.util.Map;

import bean.UserBean;

public class FindCommentLogic {
    public Map<Integer, Board> executeFindComment(UserBean ubean) {
        FindCommentSQL fcdao = new FindCommentSQL();
        Map<Integer, Board> map = fcdao.findcomment(ubean);
        return map;
    }

}