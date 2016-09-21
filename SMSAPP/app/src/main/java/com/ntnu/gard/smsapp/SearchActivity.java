package com.ntnu.gard.smsapp;

import android.app.SearchManager;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listView = (ListView) findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(arrayAdapter);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            doSearch(query);
        }
    }

    /**
     *
     * @param query
     */
    private void doSearch(String query) {
        query = query.toLowerCase();

        List<String> result = new ArrayList<>();
        for(List<Message> itemM : DomainSingleton.getSingleton(this).getData()) {
            for(Message item :itemM ){
            if(item.getMessage().toLowerCase().contains(query)) {
                result.add(item.getMessage());
            }
        }}

        doPresentResult(result);
    }


    /**
     *
     * @param results
     */
    private void doPresentResult(List<String> results) {
        arrayAdapter.addAll(results);
        arrayAdapter.notifyDataSetChanged();

    }

}
