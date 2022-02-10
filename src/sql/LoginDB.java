package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

public class LoginDB {
    // データベース接続に使用する情報
	Connection con = null;
	PreparedStatement ps = null;
    final String jdbcId = "info";
    final String jdbcPass = "pro";
    final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";

    // ログインアカウントを探す
    public UserBean findAccount(UserBean ub) {

        // 戻り値の用意
        UserBean returnUb = new UserBean();

        // データベースへ接続
        try  {

        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro");
            String sql = "SELECT user_id, user_name, user_pass FROM cloudy_user WHERE user_id = ? AND user_pass = ?";
            PreparedStatement ps= con.prepareStatement(sql);

            ps.setString(1, ub.getUserId());
            ps.setString(2, ub.getPassWord());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // 見つかったアカウント情報を戻り値にセット
                returnUb.setUserId(rs.getString("user_id"));
                returnUb.setName(rs.getString("user_name"));
                returnUb.setPassWord(rs.getString("user_pass"));
            } else {
                // アカウントがなければnullを返す
                return null;
            }
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return null;
        }
        return returnUb;
    }
}