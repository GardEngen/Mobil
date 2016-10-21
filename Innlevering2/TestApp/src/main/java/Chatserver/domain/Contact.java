package Chatserver.domain;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gard
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Contact implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.TABLE) 
    @Column(name = "CONTACT_ID")
            Long id;
    //Long id;
    String name;
    @XmlTransient
    Long conversationId;
    
   

    public Contact(){
    }

    public Contact(String name, Long conversationId) {
        //this.id = id;
        this.name = name;
        this.conversationId = conversationId;

    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        System.out.println("ID ER : " + id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    
    
}
