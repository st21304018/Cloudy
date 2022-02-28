

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Board;
import bean.UserBean;
import logic.NewLineLogic;
import main.AddCommentLogic;
import main.FindCommentLogic;

/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet("/MainPageServlet")
public class MainPageServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //リスナークラスに移動したい
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        UserBean ub = (UserBean) session.getAttribute("account");//beanを入手

        // 既存のコメントを確認
        FindCommentLogic fcl = new FindCommentLogic();
        RequestDispatcher rd =request.getRequestDispatcher("mainpage");


        Map<Integer, Board> map = null;

        try {
        	map = fcl.executeFindComment(ub);
        }catch(NullPointerException e) {
        	rd =request.getRequestDispatcher("usererror");
        }

        // セッションスコープにコメントリストを保存
        session.setAttribute("map", map);

        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力された値を取得

        String comment = request.getParameter("comment");
        String tag = request.getParameter("tag");

        HttpSession session = request.getSession();//sessionを入手
        UserBean ub = (UserBean) session.getAttribute("account");//beanを入手



        String commentNeo = NewLineLogic.htmlEscape(comment);
        String commentNewline = commentNeo.replaceAll("[\n]", "<br>");
        //JavaBeansに格納
        Board bo = new Board();

        bo.setComment(commentNewline);
        bo.setUser_id(ub.getUserId());
        bo.setTag(tag);
        // mysqlに格納
        AddCommentLogic acl = new AddCommentLogic();
        acl.executeAddComment(bo);

        // 今入力されたコメントと既存のコメントをmysqlから取得
        FindCommentLogic fcl = new FindCommentLogic();
        Map<Integer, Board> map = fcl.executeFindComment(ub);

        // セッションスコープにコメントリストを保存
        HttpSession commentSession = request.getSession();
        commentSession.setAttribute("map", map);

        RequestDispatcher rd =request.getRequestDispatcher("mainpage");
        rd.forward(request, response);

    }

}