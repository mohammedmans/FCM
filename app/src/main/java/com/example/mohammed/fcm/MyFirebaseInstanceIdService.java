package com.example.mohammed.fcm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static android.content.ContentValues.TAG;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public static final String TOKEN_BROADCAST = "myfcmtokenbroadcast";

    @Override
    // this is method is called only when token change or newly generated
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("My Firebase ID", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);

        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
        // then recieve broadcast in main activity

        storeToken(refreshedToken);
    }
    private void storeToken(String token){
        SharedPrefManager.getInstance(getApplicationContext()).storeToken(token);
    }

}
