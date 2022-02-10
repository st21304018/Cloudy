

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import main.AddCommentLogic;
import main.Board;
import main.FindCommentLogic;

/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet("/MainPageServlet")
public class MainPageServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //リスナークラスに移動したい
        request.setCharacterEncoding("UTF-8");



        // 既存のコメントを確認
        FindCommentLogic fcl = new FindCommentLogic();
        List<Board> list = fcl.executeFindComment();

        // セッションスコープにコメントリストを保存
        request.setAttribute("listAttribute", list);

        RequestDispatcher rd =request.getRequestDispatcher("mainPage");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力された値を取得

        String comment = request.getParameter("comment");

        HttpSession session = request.getSession();//sessionを入手
        UserBean ub = (UserBean) session.getAttribute("account");//beanを入手

        //JavaBeansに格納
        Board bo = new Board();

        bo.setComment(comment);
        bo.setUser_id(ub.getUserId());
        // mysqlに格納
        AddCommentLogic acl = new AddCommentLogic();
        acl.executeAddComment(bo);

        // 今入力されたコメントと既存のコメントをmysqlから取得
        FindCommentLogic fcl = new FindCommentLogic();
        List<Board> list = fcl.executeFindComment();

        // セッションスコープにコメントリストを保存
        HttpSession commentSession = request.getSession();
        commentSession.setAttribute("listAttribute", list);

        RequestDispatcher rd =request.getRequestDispatcher("MainPage.jsp");
        rd.forward(request, response);

    }

}