package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import bean.Board;
import bean.UserBean;
import logic.LikeCheckLogic;
import logic.UsernameLogic;

public class FindCommentSQL {

    public Map<Integer, Board> findcomment(UserBean ubean) {

        // id,name,commentを格納するリスト
        Map<Integer, Board> list = new LinkedHashMap<>();


        Connection con = null;

        try {

        	Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
    				"info","pro");

            System.out.println("Connected....");

            try {
                Statement st = con.createStatement();

                String sql = "select th_id, th_text, th_likes, user_id,to_char(th_date,'hh24:mi') as time from cloudy_thread order by th_id desc";


                try {
                    // sqlを送信
                    ResultSet rs = st.executeQuery(sql);

                    while (rs.next()) {
                        // DBから取り出したid,name,commentをJavaBeansにset
                        Board bo = new Board();

                        bo.setId(rs.getInt("th_id"));
                        bo.setComment(rs.getString("th_text"));
                		bo.setLikes(rs.getInt("th_likes"));
                		bo.setUser_id(rs.getString("user_id"));
                		bo.setTime(rs.getString("time"));
                		bo.setCheck(new LikeCheckLogic().likeLogic(bo, ubean));
                		bo.setUser_name(new UsernameLogic().nameLogic(bo));

                        // リストに1個ずつ格納。末尾に要素が追加されていく。
                        list.put(bo.getId(), bo);
                    }

                    rs.close();
                    st.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

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
            return null;
        }catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.out.println("Connection Failed.");
         }
        return list;

    }

}