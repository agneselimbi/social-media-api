package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import com.revature.util.ConnectionFactory;


public class MessageDAOImpl implements DAO<Message>{
    
    public Message get(String str){
        return null;
    }
    public Message get(int msgid){
        try{
            // connect to the message database 
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            //SQL statement
            String sql = "SELECT * FROM posts where msgid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, msgid);

            //Execute SQL
            ResultSet rs = ps.executeQuery();

            //Save result
            while (rs.next()) {
            }
            Message msg = new Message(
                rs.getInt("msgid"),
                rs.getString("username"), 
                rs.getString("msg"));
            return msg;
    }catch(SQLException e){
        e.printStackTrace();
        System.out.println(String.format("Unable to find Post with id %s", msgid));
        return null;

    }
}
    public List<Message> getAll() {
        try{
            // connect to the message database 
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            //SQL statement
            String sql = "SELECT * FROM posts where msgid = ?";
            Statement stmt = connection.createStatement();
                        //Execute SQL
            ResultSet rs = stmt.executeQuery(sql);

            //Save result
            List<Message> posts = new ArrayList<>();
            while (rs.next()) {
                posts.add(new Message(rs.getInt("msgid"),
                                       rs.getString("username"),
                                       rs.getString("msg")));
            }
            return posts;
    }catch(SQLException e){
        e.printStackTrace();
        System.out.println(String.format("Unable to retrieve all Posts"));
        return null;

    }
    }
    public int save(Message msg){
        try {
            //acquire a connection
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            //SQL statements
            String sql = "INSERT INTO posts (username,msg) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, msg.getUserName());
            ps.setString(2,msg.getMessage());

            // Execute query
            int result = ps.executeUpdate();
            if (result>0){
                return result;
            }else{
                System.out.println("Unable to save post");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int update(Message msg){
        try {
            //acquire a connection
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            //SQL statements
            String sql = "UPDATE posts SET username = ? AND msg= ? WHERE msgid = ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, msg.getUserName());
            ps.setString(2,msg.getMessage());
            ps.setInt(3, msg.getMsgId());

            // Execute query
            int result = ps.executeUpdate();
            if (result>0){
                return result;
            }else{
                System.out.println(String.format("Unable to update post id %s", msg.getMsgId()));
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        }
    public int delete(int msgid) {
        try {
            //acquire a connection
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            //SQL statements
            String sql = "DELETE FROM posts WHERE msgid = ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
        
            ps.setInt(1, msgid);

            // Execute query
            int result = ps.executeUpdate();
            if (result>0){
                return result;
            }else{
                System.out.println(String.format("Unable to update post id %s", msgid));
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int delete(String msgid){
        System.out.println(String.format("Unable to update post id %s", msgid));
        return -100;
    }
}