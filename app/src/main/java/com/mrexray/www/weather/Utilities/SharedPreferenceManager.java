package com.mrexray.www.weather.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ray on 11/12/2016.
 */

public class SharedPreferenceManager {
    Context context;
    SharedPreferences sharedPreferences;
    private String KEY="city";

    public SharedPreferenceManager(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(KEY,Context.MODE_PRIVATE);
    }

    public String getFromSharedPreference(){

        return sharedPreferences.getString(KEY,"");
    }
    public void setInSharedPreferences(String city){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY,city);
        editor.commit();
    }
}
