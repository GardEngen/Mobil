/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatserver.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gard
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Message implements Serializable {
    @Id @GeneratedValue
    @Column(name = "MESSAGE_ID")
    @XmlTransient
    Long id;
    
    @XmlTransient
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    Contact contact;
    @XmlTransient
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    Contact recipient;
    
    String message;
    

    public Message() {
    }
    
    public Message(Contact sender, Contact recipient, String message) {
        this.contact = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public Contact getContact() {
        return contact;
    }

    public String getMessage() {
        return message;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
//    @XmlElement(name = "sender_id")
//    public Long getSenderId() {
//        return contact.getId();
//    }

    @XmlElement(name = "Conversation_Id")
    public Long getConversationId()
    {
    return recipient.getConversationId();
    }
}
