package com.example.ssproject1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Date;

public class StringBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        String action = intent.getAction();
        Toast.makeText(context, " Time is : "+currentDateTimeString + " Action: "+action, Toast.LENGTH_LONG).show();

    }

}
