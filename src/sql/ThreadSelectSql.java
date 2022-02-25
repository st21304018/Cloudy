package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.SelectBean;
import bean.UserBean;
import logic.LikeCheckLogic;
import logic.ThreadNamelogic;

public class ThreadSelectSql {
	SelectBean sb = new SelectBean();

	public SelectBean ThreadText(String sql, UserBean ubean) {

        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YY/MM/dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat df3 = new SimpleDateFormat("YY/MM/dd HH:mm");
        String sysdate = df.format(d);
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
    		Date time = rs.getDate("th_date");
    		if(sysdate.equals(df.format(time))){
    				sb.setTime(df2.format(time));
    		}else {
    			sb.setTime(df3.format(time));
    		}
			sb.setUserid(rs.getString("user_id"));
			sb.setTag(rs.getString("th_tag"));
			sb.setName(new ThreadNamelogic().nameLogic(sb));
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
