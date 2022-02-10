package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCommentSQL {

    // DBにid,name,commentを加えるメソッド
    public AddCommentSQL(Board bo) {


        if(bo.getComment().isEmpty()) {
            bo.setComment( "コメント無し");
        }



        Connection con = null;

        try {

        	Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
    				"info","pro");

            System.out.println("Connected....");

            try {
            	String sql = "insert into cloudy_thread(th_id, th_text, th_tag,user_id) values(?, ?, ?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                Integer i = bo.getId();
                String s = i.toString();
                ps.setString(1,"102");
                ps.setString(2, bo.getComment());
                ps.setString(3,bo.getTag());
                ps.setString(4,bo.getUser_id());

                // ひな形を送信
                int r = ps.executeUpdate();

                if (r != 0) {
                    System.out.println(r + "件の書き込みを追加しました。");
                } else {
                    System.out.println("書き込みできませんでした。");
                }

                ps.close();

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

        }catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.out.println("Connection Failed.");
         }

    }

}