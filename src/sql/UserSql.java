package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.UserBean;

public class UserSql{
	Connection con = null;
    PreparedStatement ps = null;

	public void setAccount(UserBean bean, String sql) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
    				"info","pro");

            ps = con.prepareStatement(sql);

            ps.setString(1, bean.getUserId());
            ps.setString(2, bean.getName());
            ps.setString(3, bean.getPassWord());

            ps.executeUpdate();

            ps.close();
            con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			e.getMessage();
			System.out.println("クラスがないみたい。");
		}catch(SQLException e){
			e.printStackTrace();
			e.getMessage();
			System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
	}
}