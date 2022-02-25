package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.textBean;

public class ThreadInsertSql{

	public void setText(textBean bean,String sql){
		Connection con;
	    PreparedStatement ps;


    try{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//Oracleに接続する
			con=
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
			"info","pro");
		System.out.println("接続完了");

		//select文

		ps = con.prepareStatement(sql);
		
		
		
		ps.setString(1, bean.getText());
		ps.setString(2, bean.getTag());



		//Statementインターフェイスを実装するクラスをインスタンス化する

		ps.executeUpdate();

		//Oracleから切断する
		con.close();

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
