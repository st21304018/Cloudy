package main;

import bean.Board;

public class AddCommentLogic {
    public void executeAddComment(Board bo) {
        AddCommentSQL acsql = new AddCommentSQL(bo);
    }
}