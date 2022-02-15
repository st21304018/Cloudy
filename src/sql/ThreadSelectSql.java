package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.SelectBean;

public class ThreadSelectSql {
	SelectBean sb = new SelectBean();

	public SelectBean ThreadText(String sql) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"info", "pro");
			System.out.println("接続完了");

			Statement st = cn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			rs.next();
			sb.setText(rs.getString(1));
			sb.setTime(rs.getString(2));
			//sb.setUser_id(rs.getInt(3)
			//sb.setTag(rs.getString(4));
			//sb.setTh_id(rs.getInt(5));

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
