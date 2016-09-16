package com.ntnu.gard.smsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    public static final String CONVERSATION_ID = "conversationid";

    private ListView messageList;
    private List<Message> messages;
    private Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //får tak i kontaktens navn og bruk det som chattittel
        Intent i = getIntent();
        setTitle(i.getStringExtra("contactName"));

        //lagringgreier :D:D:D
        int conversationId = i.getIntExtra(CONVERSATION_ID,-1);
        DomainSingleton service = DomainSingleton.getSingleton(this);
        if(conversationId != -1) {
            messages = service.getConversation(conversationId);
        }  else {
            messages = service.createConversation();
            conversationId = service.getData().size() -1; // OBS not threadsafe
        }

            this.sendButton = (Button) findViewById(R.id.sendButton);
        // Find the list view
        this.messageList = (ListView) findViewById(R.id.messageListView);

        showMessages();


            }

    //viser alle meldingene som er lagt til i messages
    public void showMessages()
    {
        MessageListAdapter mAdapter = new MessageListAdapter(this, messages);
        messageList.setAdapter(mAdapter);

        sendButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createMessage();

            }
        });

    }

    private void createMessage()
    {

        EditText messageText = (EditText) findViewById(R.id.messageText);

        String newMessageText = messageText.getText().toString();

        if(!newMessageText.equals("")) {
            Message newMessage = new Message(newMessageText);
            messages.add(newMessage);
            messageText.setText("");
        }


    }

    //når man trykker på tilbakepila
    public void onBackPressed()
    {
     Intent i = new Intent(getApplicationContext(),ContactActivity.class);
        startActivity(i);

    }






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
