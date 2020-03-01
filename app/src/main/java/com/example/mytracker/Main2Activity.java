package com.example.mytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private Button easy_btn;
    private Button medium_btn;
    private Button hard_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        easy_btn = (Button) findViewById(R.id.button);
        medium_btn = (Button) findViewById(R.id.button1);
        hard_btn = (Button) findViewById(R.id.button2);
        easy_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchEasy();
            }
        });
        medium_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchMedium();
            }
        });
        hard_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchHard();
            }
        });
    }
    private void launchEasy(){
        Intent intent = new Intent(this, BeginnerActivity.class);
        startActivity(intent);
    }
    private void launchMedium(){
        Intent intent = new Intent(this, MediumActivity.class);
        startActivity(intent);
    }
    private void launchHard(){
        Intent intent = new Intent(this, HardActivity.class);
        startActivity(intent);
    }
}
