package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import bean.Board;
import bean.UserBean;
import logic.LikeCheckLogic;
import logic.UsernameLogic;

public class FindCommentSQL{

    public Map<Integer, Board> findcomment(UserBean ubean, String sql) {

        // id,name,commentを格納するリスト
        Map<Integer, Board> list = new LinkedHashMap<>();
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YY/MM/dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat df3 = new SimpleDateFormat("YY/MM/dd HH:mm");
        String sysdate = df.format(d);


        Connection con = null;

        try {

        	Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
    				"info","pro");

            System.out.println("Connected....");

            try {
                Statement st = con.createStatement();


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
                		Date time = rs.getDate("th_date");
                		if(sysdate.equals(df.format(time))){
                				bo.setTime(df2.format(time));
                		}else {
                			bo.setTime(df3.format(time));
                		}
                		bo.setTag(rs.getString("th_tag"));
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