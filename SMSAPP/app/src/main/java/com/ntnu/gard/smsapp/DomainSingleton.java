package com.ntnu.gard.smsapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gard on 16.09.2016.
 */

public class DomainSingleton {
    public static DomainSingleton SINGLETON;

    private List<List<Message>> data = new ArrayList<>();

    private DomainSingleton() {}


    public static synchronized DomainSingleton getSingleton(Context context) {
        if(SINGLETON == null) {
            SINGLETON = new DomainSingleton();
        }

        return SINGLETON;
    }

    public synchronized List<List<Message>> getData() {
        return data;
    }

    public synchronized List<Message> getConversation(int conversationId) {
        return getData().get(conversationId);
    }

    public synchronized List<Message> createConversation() {
        List<Message> result = new ArrayList<>();
        getData().add(result);
        return result;
    }
    //ikke i bruk enda
    public synchronized String getContactName(int conversationId)
    {
        Message message1 = getData().get(conversationId).get(0);
        String contactName = message1.getName();
        System.out.println("DETTE ER CONVERSATION ID TIL " +
               contactName + " : " + message1.getConversationId());
        return contactName;

    }
    //Får tak i den første meldingen i en samtale.
    public synchronized Message getFirstMessageInConversation(int dataIndex)
    {
        Message firstMessage = getData().get(dataIndex).get(0);
        return firstMessage;
    }
    //returnerer en liste med alle personen man har en samtale med
    public synchronized List<String> getAllConversationNames()
    {
        List<String>allnames = new ArrayList<String>();
        for(int i = 0; getData().size() > i; i++)
        {
            String name = getFirstMessageInConversation(i).getName();
            if(!allnames.contains(name)) {
                allnames.add(name);
            }
        }
       return allnames;
    }

}
