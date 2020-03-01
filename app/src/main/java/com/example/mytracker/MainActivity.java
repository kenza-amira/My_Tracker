package com.example.mytracker;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView countView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private Button mBtLaunchActivity;
    private Button mBtMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numSteps = 0;
        countView = findViewById(R.id.tv_steps);
        mBtMe = (Button) findViewById(R.id.bt_launch_me);
        mBtLaunchActivity = (Button) findViewById(R.id.bt_launch_activity);
        mBtLaunchActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchActivity();
            }
        });


        mBtMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchProfile();
            }
        });

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);


        Button BtnStart = findViewById(R.id.btn_start);
        Button BtnStop = findViewById(R.id.btn_stop);



        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                countView.setText(TEXT_NUM_STEPS + "0");

            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(MainActivity.this);
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        Log.d("Debug", "Work???");
        numSteps++;
        countView.setText(TEXT_NUM_STEPS + numSteps);
    }
    private void launchActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    private void launchProfile(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

}
