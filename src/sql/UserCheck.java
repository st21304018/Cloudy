package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCheck{
	Connection con = null;
    PreparedStatement ps = null;
    String check;

	public String userCheck(String sql) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
    				"info","pro");

            ps = con.prepareStatement(sql);

            ResultSet result = ps.executeQuery();
            result.next();
            check = result.getString(1);

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
		if(check.equals("0")) {
			return null;
		}else {
			return "1";
		}

	}
}