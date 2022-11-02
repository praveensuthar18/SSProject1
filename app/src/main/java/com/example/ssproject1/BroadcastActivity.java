package com.example.ssproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class BroadcastActivity extends AppCompatActivity {

    StringBroadcastReceiver receiver;
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        receiver = new StringBroadcastReceiver();
        intentFilter = new IntentFilter("com.example.homework");

    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }
    public void onClick(View v){

        if(v.getId() == R.id.brdbutton) {
            Intent intent = new Intent("com.example.homework");
            sendBroadcast(intent);
        }

    }
}