package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import bean.SelectBean;
import bean.UserBean;
import logic.LikeCheckLogic;

public class ReplySelectSql {

	public Map<Integer,SelectBean> replySelect(String sql, UserBean ubean) {

		Map<Integer,SelectBean> map = new LinkedHashMap<Integer,SelectBean>();

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");


			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"info", "pro");
			System.out.println("接続完了");

			System.out.println("Connected....");

			Statement st = cn.createStatement();


			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				SelectBean sb = new SelectBean();
				sb.setText(rs.getString("reply_text"));
				sb.setTime(rs.getString("reply_date"));
				sb.setTag(rs.getString("reply_tag"));
				sb.setTh_id(rs.getInt("th_id"));
				sb.setReply_id(rs.getInt("reply_id"));
				sb.setLikes(rs.getInt("reply_likes"));
				sb.setCheck(new LikeCheckLogic().likeRepLogic(sb, ubean));

				map.put(sb.getReply_id(),sb);
			}

			rs.close();
			st.close();

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
		return map;

	}

}
