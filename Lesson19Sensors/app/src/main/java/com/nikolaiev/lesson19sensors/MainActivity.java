package com.nikolaiev.lesson19sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float steps;
    private TextView mTextView;
    private List<Sensor> mSensorList;
    SensorEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < mSensorList.size(); i++) {
            Log.d(TAG, "onCreate: " + mSensorList.get(i).getName());
        }
        mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                switch (event.sensor.getType()) {
                    case Sensor.TYPE_STEP_COUNTER: {
                        steps = event.values[0];
                        mTextView.setText(String.valueOf(steps));
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        mSensorManager.registerListener(eventListener,mSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(eventListener);
    }
}
