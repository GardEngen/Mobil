package com.ntnu.gard.lab2;

import java.io.Serializable;

/**
 * Created by Gard on 02.09.2016.
 */

public class Contact implements Serializable {
    String firstName;

    public Contact(String firstName) {
        this.firstName = firstName;
    }
}

