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
}
