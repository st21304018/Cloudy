package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.UserBean;

public class LoginDB {
    // データベース接続に使用する情報
    final String jdbcId = "root";
    final String jdbcPass = "password";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=JST";

    // ログインアカウントを探す
    public UserBean findAccount(UserBean ub) {

        // 戻り値の用意
        UserBean returnUb = new UserBean();

        // データベースへ接続
        try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass)) {

            String sql = "SELECT user_id, user_name, user_pass FROM cloudy_user WHERE loginId = ? AND pass = ?";
            PreparedStatement ps= con.prepareStatement(sql);

            ps.setString(1, ub.getUserId());
            ps.setString(2, ub.getPass());

            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                // 見つかったアカウント情報を戻り値にセット
                returnUb.setUserId(rs.getString("user_id"));
                returnUb.setName(rs.getString("user_name"));
                returnUb.setPass(rs.getString("user_pass"));
            } else {
                // アカウントがなければnullを返す
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return returnUb;
    }
}