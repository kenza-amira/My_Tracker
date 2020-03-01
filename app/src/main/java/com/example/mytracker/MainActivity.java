package com.example.mytracker;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private TextView tvSteps2;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    private Button mBtLaunchActivity;
    private Button mBtMe;
    private Button home;

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        numSteps = settings.getInt("steps",numSteps);
        countView = findViewById(R.id.tv_steps);
        mBtMe = (Button) findViewById(R.id.bt_launch_me);
        mBtLaunchActivity = (Button) findViewById(R.id.bt_launch_activity);
        mBtLaunchActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchActivity();
            }
        });

        home = (Button) findViewById(R.id.tour);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchHome();
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



    public int points(int stepNo){
        if (numSteps % 1000 == 0) {
            tvSteps2.setText("Congratulations you have achieved " + stepNo/100 + " points!");
            return stepNo/100;
        }
        return stepNo = 0;



    }

    @Override
    public void step(long timeNs) {
        Log.d("Debug", "Work???");
        numSteps++;
        countView.setText(TEXT_NUM_STEPS + numSteps);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("steps",numSteps);
        editor.commit();


        int pointTotal = points(numSteps);
    }

    private void launchActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    private void launchProfile(){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    private void launchHome(){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
