package main;

import java.util.List;

public class FindCommentLogic {
    public List<Board> executeFindComment() {
        FindCommentSQL fcdao = new FindCommentSQL();
        List<Board> list = fcdao.findcomment();
        return list;
    }

}