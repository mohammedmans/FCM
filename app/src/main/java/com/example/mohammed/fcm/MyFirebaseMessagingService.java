package com.example.mohammed.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM_Message";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        notifyUser(remoteMessage.getFrom(), remoteMessage.getNotification().getBody());

        writeToDB(remoteMessage.getNotification().getBody());


    }

    public void notifyUser(String from, String notification) {
        MyNotificationManager myNotificationManager =
                new MyNotificationManager(getApplicationContext());
        myNotificationManager.showNotification(from, notification,
                new Intent(getApplicationContext(), MainActivity.class));

    }

    public void writeToDB(String Message) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference().child("user");

        myRef.setValue(Message);
    }

}
