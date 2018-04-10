package com.example.mohammed.fcm;


import android.content.Context;
import android.content.SharedPreferences;

// this class to save the token and give us the token back
// using singleton pattern
public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "fcmshareddemo";
    // key of the token
    private static final String KEY_ACCESS_TOKEN = "token";
    private static Context mCtx;
    private static SharedPrefManager mInstance;

    // constructor
    private SharedPrefManager(Context context){
        mCtx = context;
    }
    // return instance of this class
    public static synchronized SharedPrefManager getInstance(Context context){
        if(mInstance == null)
            // create new instance
            mInstance = new SharedPrefManager(context);

        return mInstance;

    }

    // store token to shared pref
    public boolean storeToken (String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN,token);
        editor.apply();
        return true;
    }

    // store back from shred pref
    public String getToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(KEY_ACCESS_TOKEN,null);

    }
}
