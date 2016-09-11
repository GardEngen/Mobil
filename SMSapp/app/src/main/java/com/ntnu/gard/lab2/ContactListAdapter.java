package com.ntnu.gard.lab2;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import android.provider.ContactsContract;


/**
 * Created by Gard on 02.09.2016.
 */

public class ContactListAdapter extends ArrayAdapter<Contact> {
    public ContactListAdapter(Context context, List<Contact> contactList) {
        super(context, 0, contactList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.firstName);

    //heid
        name.setText(contact.firstName);
       // name.setText(ContactsContract.CommonDataKinds.Phone.NUMBER);


        return convertView;
    }
}
