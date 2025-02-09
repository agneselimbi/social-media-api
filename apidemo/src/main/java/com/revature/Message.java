package com.revature;

public class Message {
    private int msgid;
    private String username;
    private String msg;

    public Message(String username, String msg){
        this.username = username;
        this.msg = msg;
    }

    //getter and setter methods
    public String getMessage(){
        return this.msg;
    }

    public void setMessage(String msg, int msgid){
        this.msg = msg;
        this.msgid = msgid;
    }

}
