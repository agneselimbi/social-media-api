package com.revature;
import java.util.List;
import java.util.ArrayList;
import com.revature.util.ConnectionFactory;
import java.sql.SQLException;
import java.sql.*;

public class UserDAOImpl implements DAO<User> {
    
    public User get(String username) {
        try {
            // acquire connection 
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            // sql statement
            String sql = "SELECT username, password FROM users WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            
            //Execte the query
            ResultSet rs = ps.executeQuery();
            
            // Return user 
            String password = null;
            while (rs.next()){
                password = rs.getString("password");
            }
            User user = new User(username,password);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<User> getAll(){
        try {
            //acquire connection 
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            //sql statement
            String sql = "SELECT * FROM users";
            Statement stmt = connection.createStatement();

            //Execute query
            ResultSet rs = stmt.executeQuery(sql);

            //Collect results 
            List<User> users = new ArrayList<>();
            while (rs.next()){
                users.add(new User(rs.getString("username"), rs.getString("password")));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }   
    public int save(User user) {
        try {
            // get connection
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();

            //SQL statement
            String sql = "INSERT INTO users (username, password) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2, user.getPassword());

            //Result 
            int result = ps.executeUpdate();
            if(result>0){
                String successmsg = String.format("Successfully added %s rows to database", result);
                System.out.println(successmsg);
            }
            else{
                System.out.println("Unable to add new user to the database");
            }
            return result;

            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int update(User user){
        try {
            //get connection 
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();
        
            //SQL statement
            String sql = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());

            //Result
            int result = ps.executeUpdate();
            if(result>0){
                String successmsg = String.format("Successfully updated %s's password in data database",user.getUserName() );
                System.out.println(successmsg);
                return result;
            }
            else{
                System.out.println("Unable to delete user from the database");
                return -1;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int delete(String username){
        try {
            //get connection 
            Connection connection = ConnectionFactory.getConnectionFactory().getConnection();
        
            //SQL statement
            String sql = "DELETE FROM users WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            //Result
            int result = ps.executeUpdate();
            if(result>0){
                String successmsg = String.format("Successfully deleted %s from data database", username);
                System.out.println(successmsg);
            }
            else{
                System.out.println("Unable to delete user from the database");
            }
            return result;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}


