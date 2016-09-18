package com.ntnu.gard.smsapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    // The ListView
    private ListView contactList;

    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setTitle("Contacts");

        // Find the list view
        this.contactList = (ListView) findViewById(R.id.contactList);

        // Read and show the contacts
        showContacts();

//-------------------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


//-------------------------------------------------------------------
//-------------------------------------------------------------------

    /**
     * Show the contacts in the ListView.
     */
    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {

            // Android version is lesser than 6.0 or the permission is already granted.
            List contacts = getContactNames();
            final ContactAdapter cAdapter = new ContactAdapter(this, contacts);
            contactList.setAdapter(cAdapter);
            contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                   // setContentView(R.layout.activity_chat);
                    //Toast.makeText(ContactActivity.this, "Hei", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), ChatActivity.class);

                    //Få tak i navnet på kontakten man har valgt, og send den videre til chatActivity
                    String contactName = cAdapter.getItem(position).getName();
                    i.putExtra("contactName", contactName);
                    startActivity(i);

                }
            });



        }
    }

    /**
     * Read the name of all the contacts.
     *
     * @return a list of names.
     */
    private List getContactNames() {
        List<Contact> contacts = new ArrayList<>();
        // Get the ContentResolver
        ContentResolver cr = getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {


            //Sjekker om kontakt har et tlf nummer
            String hasNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER));
            String contactName = "";
            if(hasNumber.equals("1")) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                Contact contact = new Contact(contactName);
                contacts.add(contact);


        }}
        cursor.close();
        return contacts;
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //når man trykker på tilbakepila
    public void onBackPressed()
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }

    //-------------------------------------------------------------------
    //-------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
