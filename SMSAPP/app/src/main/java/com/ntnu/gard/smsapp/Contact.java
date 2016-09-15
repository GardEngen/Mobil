package com.ntnu.gard.smsapp;

import android.net.Uri;

import java.io.Serializable;


/**
 * Created by Gard on 14.09.2016.
 */

public class Contact implements Serializable {

    private String name;

    public Contact(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
