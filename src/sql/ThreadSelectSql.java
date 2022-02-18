package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.SelectBean;
import bean.UserBean;
import logic.LikeCheckLogic;

public class ThreadSelectSql {
	SelectBean sb = new SelectBean();

	public SelectBean ThreadText(String sql, UserBean ubean) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"info", "pro");
			System.out.println("接続完了");

			Statement st = cn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			rs.next();
			sb.setText(rs.getString("th_text"));
			sb.setTime(rs.getString("th_date"));
			sb.setUserid(rs.getInt("user_id"));
			sb.setTag(rs.getString("th_tag"));
			sb.setLikes(rs.getInt("th_likes"));
			sb.setCheck(new LikeCheckLogic().likeRepLogic(sb, ubean));

			cn.close();

			System.out.println("切断完了");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("クラスがないみたい。");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;
	}

}
