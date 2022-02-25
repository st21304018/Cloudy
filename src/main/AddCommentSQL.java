package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Board;

public class AddCommentSQL {

    // DBにid,name,commentを加えるメソッド
    public AddCommentSQL(Board bo) {


        if(bo.getComment().isEmpty()) {
            bo.setComment( "コメント無し");
        }

        if(bo.getTag().isEmpty()) {
        }else {
        	String tag = bo.getTag();
        	bo.setTag("#" + tag);
        }



        Connection con = null;

        try {

        	Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
    				"info","pro");

            System.out.println("Connected....");




            try {
            	String sql = "insert into cloudy_thread(th_id, th_text, th_tag,user_id) values(th_seq.nextval, ?, ?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, bo.getComment());
                ps.setString(2,bo.getTag());
                ps.setString(3,bo.getUser_id());

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