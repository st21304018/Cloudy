package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Threadsql{
	public static void main(String[] args){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Oracleに接続する
			Connection cn=
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
				"info","pro");
			System.out.println("接続完了");

			
		
			
			String replysql="select reply_text, reply_tag, reply_date,reply_likes from cloudy_reply";

			Statement st=cn.createStatement();


			ResultSet rs=st.executeQuery(replysql);


			rs.next();
			String reply_text=rs.getString (1);	//1列目のデータを取得
			String reply_tag=rs.getString(2);	//2列目のデータを取得
			String reply_date=rs.getString(3);	//3列目のデータを取得
			int reply_likes=rs.getInt(4);
			String reply_next_id=rs.getString(5);



		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("クラスがないみたい。");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("SQL関連の例外みたい。");
	}
	}
}
