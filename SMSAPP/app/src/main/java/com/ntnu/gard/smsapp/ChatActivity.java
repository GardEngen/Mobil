package com.ntnu.gard.smsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView messageList;
    private List<Message> messages = new ArrayList<>();
    private Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Toast.makeText(ChatActivity.this, "Hei", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createMessage()
    {

        EditText messageText = (EditText) findViewById(R.id.messageText);

        String newMessageText = messageText.getText().toString();
        Message newMessage = new Message(newMessageText);
               messages.add(newMessage);
        messageText.setText("");


    }



}
