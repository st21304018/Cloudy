package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ThreadSelectSql {
	public void getThreadText(){
		String text;
		String tag;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
				"info","pro");
			System.out.println("接続完了");

			Statement st=cn.createStatement();

			String sql=" SELECT reply_text, reply_tag FROM emp";

			ResultSet rs=st.executeQuery(sql);


			rs.next();

			text=rs.getString(1);
			tag=rs.getString(2);

			cn.close();

			System.out.println("切断完了");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("クラスがないみたい。");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
