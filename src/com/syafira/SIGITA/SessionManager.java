package com.syafira.SIGITA;

/**
 * Created by syafira rarra on 04/19/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    // SharedPreferences file name
    private static final String PREF_NAME = "SIGITA_PREF";

    // Editor for Shared preferences
    Editor editor;

    // Create Session
    public void createSession(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // Check Session
    public boolean checkSession(Context context) {
        boolean check = false;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        if(prefs.contains("id")){
            check = true;
        }

        return check;
    }

    // Load Session
    public String loadSession(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    // Clear Session
    public void clearSession(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();

        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}