package com.example.hp.motionsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textview=null;
    SensorManager sm=null;
    List list;

    SensorEventListener sel=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {


            float[] values=event.values;
            textview.setText("x:"+values[0]+"\n"+"y:"+values[1]+"\n"+"z:"+values[2]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm=(SensorManager)getSystemService(SENSOR_SERVICE);

        textview=(TextView)findViewById(R.id.textView);

        list= sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size()>0)
        {
            sm.registerListener(sel,(Sensor)list.get(0),SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            Toast.makeText(MainActivity.this, "Error no Accelerometer", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onStop() {

        if (list.size()>0)
        {
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}
