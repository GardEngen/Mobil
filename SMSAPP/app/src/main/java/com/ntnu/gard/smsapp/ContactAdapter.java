package com.ntnu.gard.smsapp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, List<Contact> contact) {
        super(context, 0, contact);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_layout,parent,false);
        }


        //ImageView person = (ImageView)convertView.findViewById(R.id.imageView);
        TextView messageView = (TextView)convertView.findViewById(R.id.textView);

        messageView.setText(contact.getName());

        return convertView;
    }
}
