package com.example.ssproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    TextView timeView;
    Button start, stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        timeView = (TextView) findViewById(R.id.timeview);
        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String curr_time = intent.getStringExtra(TimeService.CURR_TIME);

                        timeView.setText("Current Time: " + curr_time);
                    }
                }, new IntentFilter(TimeService.ACTION_TIME_BROADCAST)
        );

        start = (Button) findViewById(R.id.startbtn);
        stop = (Button) findViewById(R.id.stopbtn);


    }

    public void onClick(View v){

        if(v.getId() == R.id.startbtn){
            startService(new Intent(this, TimeService.class));

        }else if(v.getId() == R.id.stopbtn){
            stopService(new Intent(this, TimeService.class));

        }

    }
}