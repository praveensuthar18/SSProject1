package com.example.ssproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    Button broadcast_btn,service_btn,content_btn;
    TextView time_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcast_btn = (Button)findViewById(R.id.broadcast_button);
        service_btn = (Button)findViewById(R.id.service_button);
        content_btn = (Button)findViewById(R.id.content_button);

        time_view = (TextView) findViewById(R.id.time);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        time_view.setText(currentDateTimeString);

    }

    public void onClick(View v){

        if(v.getId() == R.id.broadcast_button){
            Intent intent = new Intent(this, BroadcastActivity.class);
            startActivity(intent);

        }else if(v.getId() == R.id.service_button){
            Intent intent = new Intent(this, ServiceActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.content_button){
            Intent intent = new Intent(this, ContentProviderActivity.class);
            startActivity(intent);
        }

    }
}