package com.revature;

public class User {
    private String username;
    private String password;

    public User(String username, String passwd){
        this.username = username;
        this.password= passwd;
    }

    // getter and setters
    public String getUserName(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUserName(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}
