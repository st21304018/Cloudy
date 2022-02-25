package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import bean.Board;
import bean.UserBean;

public class MyCommentSQL {

	public Map<Integer, Board> findcomment(UserBean ubean) {


		// id,name,commentを格納するリスト
		Map<Integer, Board> list = new LinkedHashMap<>();
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("YY/MM/dd");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
		SimpleDateFormat df3 = new SimpleDateFormat("YY/MM/dd HH:mm");
		String sysdate = df.format(d);

		Connection con = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"info", "pro");

			System.out.println("Connected....");

			try {
				Statement st = con.createStatement();
				String sql = "SELECT th_id, th_text, th_tag, th_date, user_id from cloudy_thread WHERE user_id = ? ORDER BY th_id DESC";
				PreparedStatement ps= con.prepareStatement(sql);
                ps.setString(1, ubean.getUserId());


				try {
					// sqlを送信
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						// DBから取り出したid,name,commentをJavaBeansにset
						Board bo = new Board();

						bo.setId(rs.getInt("th_id"));
						bo.setComment(rs.getString("th_text"));
						bo.setTag(rs.getString("th_tag"));
						Date time = rs.getDate("th_date");
                		if(sysdate.equals(df.format(time))){
                				bo.setTime(df2.format(time));
                		}else {
                			bo.setTime(df3.format(time));
                		}
						bo.setUser_id(rs.getString("user_id"));

						// リストに1個ずつ格納。末尾に要素が追加されていく。
						list.put(bo.getId(), bo);
					}

					rs.close();
					st.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// データベース接続の切断
				if (con != null) {
					try {
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection Failed.");
			return null;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.out.println("Connection Failed.");
		}
		return list;

	}

}
