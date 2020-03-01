package com.example.mytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {
    private Button myMap;
    private Button mySteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        myMap = (Button) findViewById(R.id.bt_launch_map);
        myMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchMap();
            }
        });
        mySteps = (Button) findViewById(R.id.bt_launch_steps);
        mySteps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchPedometer();
            }
        });
    }
    private void launchMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    private void launchPedometer(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
