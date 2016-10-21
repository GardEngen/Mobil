/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatserver.domain;

/**
 *
 * @author Gard
 */
import java.util.ArrayList;
public class Conversation {
    private int conversationId;
    private ArrayList<Message> messages;

    public Conversation() {
    }

    public Conversation(int conversationId) {
        this.conversationId = conversationId;
        //messages = new ArrayList<Messages>
    }
    
    
    
}
