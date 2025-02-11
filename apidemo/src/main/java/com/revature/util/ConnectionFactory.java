package com.revature.util;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final ConnectionFactory connectionfactory = new ConnectionFactory();
    private Properties props = new Properties();

    // Private constructor
    private ConnectionFactory(){
        try{
            props.load(new FileReader("/home/agnes-linux/Documents/repos/social-media-api/apidemo/src/main/resources/db.properties"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getConnectionFactory(){
        return connectionfactory;
    }
    
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(
                props.getProperty("url"),    
                props.getProperty("username"), 
                props.getProperty("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
