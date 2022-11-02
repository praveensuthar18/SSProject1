package com.example.ssproject1;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Date;

public class TimeService extends Service {

    public static final String
            ACTION_TIME_BROADCAST = TimeService.class.getName() + "TimeBroadcast",
            CURR_TIME = "";

    private final Handler handler = new Handler();


    private static final int
            MIN_TIME = 2000,
            MIN_DISTANCE = 1;


    @Override
    public void onStart(Intent intent, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second

    }
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("In Timeservice oncreate method...");
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        sendBroadcastMessage(currentDateTimeString);

    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
            sendBroadcastMessage(currentDateTimeString);
            handler.postDelayed(this, 10000); // 10 seconds
        }
    };

    private void sendBroadcastMessage(String time) {
        if (time != null) {
            Intent intent = new Intent(ACTION_TIME_BROADCAST);
            intent.putExtra(CURR_TIME, time);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}