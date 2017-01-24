package com.example.magfieldget;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensormanager;
    private Sensor mMagField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //地磁気センサーをセット
        mMagField = mSensormanager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //リスナーをセット
        mSensormanager.registerListener(this,mMagField,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensormanager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("X方向:" + event.values[0] + "μT \n");
        stringBuilder.append("Y方向:" + event.values[1] + "μT \n");
        stringBuilder.append("Z方向:" + event.values[2] + "μT \n");

        TextView txt01 = (TextView) findViewById(R.id.txt01);
        txt01.setText(stringBuilder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
