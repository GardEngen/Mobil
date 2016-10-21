package Chatserver;

import Chatserver.domain.Contact;
import Chatserver.domain.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Gard
 */
@Path("chat")
@Produces("application/json")
@Stateless
public class ChatService {
    
    //Contact tempContact = new Contact("taaeasd");
     //Message tempMessage = new Message ("contact", "Hei, jeg heter gard", 44);
    
     @PersistenceContext
    EntityManager em;
     
     @GET
     public String chatInfo()
     {
     String info = " http://localhost:8080/TestApp/services/chat/: + \n getall/contact, getall/messages , message/create, contact/create";
     return info;
     }

   
   @GET
   @Path("getall/contact")
    public List<Contact> getAllContact() {
        List<Contact> list = em.createQuery("SELECT c from Contact c").getResultList();
        return list;
    }
    @GET
   @Path("getall/messages")
    public List<Message> getAllMessage() {
        return em.createQuery("SELECT m FROM Message m").getResultList();
    }
    //http://localhost:8080/TestApp/services/chat/contact/create?conversationId=3&contactName=Pappa
    @GET
    @Path("contact/create")
    public Contact createContact(
             @QueryParam("conversationId") Long conversationId,
             @QueryParam("contactName") String contactName)
    {
        System.out.println("contactId: " + conversationId );
        System.out.println("contactName: " + contactName );
        
        //em.createNativeQuery("ALTER TABLE CONTACT ALTER COLUMN ID RESTART WITH 0").executeUpdate();
        
        Contact contact = new Contact(contactName,conversationId );
        
        em.persist(contact);
        return contact;
    }
    
    
    //http://localhost:8080/TestApp/services/chat/message/create?userid=345&receiver=345&text=hei
    @GET
    @Path("message/create")
    public Message createMessage(
            @QueryParam("userid") Long userid, 
            @QueryParam("receiver") Long recipientid,
            @QueryParam("text") String text) {
        
        System.out.println("userid: " + userid );
        System.out.println("recipientid: " + recipientid );
        System.out.println("text: " + text );
        Contact sender = em.find(Contact.class, userid);
        Contact recipient = em.find(Contact.class,recipientid);
        
        if(sender == null)
        {
            System.out.println("SENDER ER NULL");
        }
        if(recipient == null)
        {
            System.out.println("recipient ER NULL");
        }
       
        
        Message message = new Message(sender, recipient, text);
        if((message == null))
        {
             System.out.print("message er null");
             
        }
     em.persist(message);
       
        
        return message;
    }
   
    
    
    
//    @GET
//    @Path("user2")
//    public Contact getUser2(@QueryParam("name") String name) {    
//        return new Contact("Mikael","");
//    }
    
//    @GET
//    @Path("test/{user}-{id}")
//    public Contact getInfoAboutUser(@PathParam("user") String name,@PathParam("id") String id)
//    {// http:/asdfsadf/services/chat/mikael-273
//        return tempContact;
//    }

//@GET
//@Path("(user)")
//public List<User> findUser(@QueryParam("name") String name){
//}
}