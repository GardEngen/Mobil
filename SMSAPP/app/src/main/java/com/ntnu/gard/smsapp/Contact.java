package com.ntnu.gard.smsapp;

import java.io.Serializable;

/**
 * Created by Gard on 14.09.2016.
 */

public class Contact implements Serializable {

    String name;

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
