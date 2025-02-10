package com.revature;

public class Message {
    private int msgid;
    private String username;
    private String msg;

    public Message(int msgid, String username, String msg){
        this.msgid= msgid;
        this.username = username;
        this.msg = msg;
    }

    //getter and setter methods
    public String getMessage(){
        return this.msg;
    }

    public String getUserName(){
        return this.username;
    }

    public int getMsgId(){
        return this.msgid;
    }

    public void setMessage(String msg){
        this.msg = msg;
    }

    public void setUserName(String username){
        this.username = username;
    }
   

}
