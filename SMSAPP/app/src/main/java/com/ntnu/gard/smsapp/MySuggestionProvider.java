package com.ntnu.gard.smsapp;

import android.content.SearchRecentSuggestionsProvider;
import android.util.Log;

/**
 * Created by Gard on 18.09.2016.
 */

public class MySuggestionProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.ntnu.gard.smsapp.MySuggestionProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;  // Configures database to record recent queries

    public MySuggestionProvider() {
        Log.i("Search","MySuggestionProvider");
        setupSuggestions(AUTHORITY,MODE);
    }
}
