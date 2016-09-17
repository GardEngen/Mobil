package com.ntnu.gard.smsapp;


import java.io.Serializable;
import java.util.Date;


public class Message implements Serializable {
    String name;
    String message;
    Date timestamp;
    Integer conversationId;

    public Message(String message, String name, int conversationId) {
        this.name = name;
        this.message = message;
       // this.timestamp = new Date();
        this.conversationId = conversationId;

    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public Integer getConversationId(){return conversationId;}


}