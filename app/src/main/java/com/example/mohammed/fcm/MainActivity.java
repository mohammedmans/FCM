package com.example.mohammed.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private BroadcastReceiver broadcastReceiver;
    private Button button;
    private Messages messages;
    private MyFirebaseMessagingService myFirebaseMessagingService;
    DatabaseReference mDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textViewToken);
        button = (Button) findViewById(R.id.btn);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // this method will excute when broadcast send from MYFirebase
                textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            }
        };

        if (SharedPrefManager.getInstance(this).getToken() != null) {
            textView.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            Log.d("myfcmtokenshared", SharedPrefManager.getInstance(this).getToken());


        }

        registerReceiver(broadcastReceiver, new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));

        

    }

}
