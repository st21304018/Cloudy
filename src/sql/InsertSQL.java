package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSQL{
	Connection con = null;
    PreparedStatement ps = null;

	public void insertSQL(String sql) {

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
    				"info","pro");

            ps = con.prepareStatement(sql);
            ps.executeUpdate();

            System.out.println("書き込み完了");
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
