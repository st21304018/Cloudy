package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.SelectBean;

public class ReplySelectSql {

	public List<SelectBean> replySelect(String sql) {

		List<SelectBean> list = new ArrayList<>();

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"info", "pro");
			System.out.println("接続完了");

			System.out.println("Connected....");

			Statement st = cn.createStatement();

			// sqlを送信
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				// DBから取り出したid,name,commentをJavaBeansにset
				SelectBean sb = new SelectBean();
				sb.setText(rs.getString(1));
				//bo.setName(rs.getString("name"));
				//bo.setComment(rs.getString("comment"));
				//bo.setTime(rs.getTimestamp("time"));

				// リストに1個ずつ格納。末尾に要素が追加されていく。
				list.add(sb);
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
		return list;

	}

}
