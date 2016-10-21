package com.ntnu.gard.smsapp;


import java.io.Serializable;
import java.util.Date;


public class Message implements Serializable {
    String contact;
    String message;
    Date timestamp;
    Integer conversationId;

    public Message(String message, String contact, int conversationId) {
        this.contact = contact;
        this.message = message;
        // this.timestamp = new Date();
        this.conversationId = conversationId;

    }

    public String getContact() {
        return contact;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getConversationId() {
        return conversationId;
    }


}