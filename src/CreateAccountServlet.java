import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import sql.UserCheck;
import sql.UserSql;

public class CreateAccountServlet extends HttpServlet{

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//POST要求によって送信された文字列をクライアントで
		//エンコードしたときの文字コードを指定する
		//これを指定しないと文字化けする可能性がある
		req.setCharacterEncoding("UTF-8");

		//POST要求によって送信されたパラメータを取得する
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String pass=req.getParameter("pass");

		//クラスメソッドhashingでpassをハッシュ化
		pass = hash.Hashing.hashing(pass);


		//Beanのインスタンスを作成してname, id, passをセットする
		UserBean bean = new UserBean();
		bean.setName(name);
		bean.setUserId(id);
		bean.setPassWord(pass);

		//UserCheckをインスタンス化してidをチェックするためのsql文を渡す
		String checkSql = "select count(user_id) as count from cloudy_user where user_id='"+id+"'";
		UserCheck user = new UserCheck();
		//userCheckから戻り値として、IDがすでにあった場合1をそうでない場合0を受け取る、int型に変換する
		String check = user.userCheck(checkSql);

		//userIDがすでに存在した場合createacountに戻す、そうでない場合アカウント情報をSQLにセットしてresultを表示する
		if(check != null) {
			String errormess = "このユーザーIDは既に使われています";
			req.setAttribute("error", errormess);
			RequestDispatcher dispatcher =
				req.getRequestDispatcher("createaccount");
			dispatcher.forward(req, res);
		}else {
			//UserSqlをインスタンス化し、Sqlにデータをセットする
			String sql = "INSERT INTO CLOUDY_USER VALUES(?, ?, ?)";
			UserSql usersql = new UserSql();
			usersql.setAccount(bean, sql);

			req.setAttribute("name",name);
			req.setAttribute("id",id);
			req.setAttribute("pass",pass);

			//RequestDispatcherインターフェイスを実装するクラスの
			//インスタンスを取得する
			//引数は転送先のURL
			RequestDispatcher dispatcher=
				req.getRequestDispatcher("createaccountresult");

			//転送先に要求を転送する
			dispatcher.forward(req,res);
		}
	}
}
