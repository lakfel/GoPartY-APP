package cam.grupo09.goparty.Sensores;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import cam.grupo09.goparty.activities.MainActivity;

/**
 * Created by Felipe on 16/05/2016.
 */
public class ServicioTaxiAcel implements SensorEventListener
{
    private MainActivity activity;
    private SensorManager mSensor;
    private float x, y, z, xa, ya, za;
    private boolean primera, pp;


    public ServicioTaxiAcel()
    {
         pp = true;
    }

    public void start(MainActivity activity)
    {
        this.activity = activity;
        primera = true;
        mSensor = (SensorManager) activity.getSystemService(activity.SENSOR_SERVICE);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    public void unreg()
    {
        mSensor.unregisterListener(this);
    }

    public void reg()
    {
        mSensor.registerListener(this,
                mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (pp)
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            pp= false;
            float values[] = event.values;
            if(!primera)
            {

                primera = false;

            }
            else
            {
                x = Math.abs(values[0]-xa);
                y = Math.abs(values[1]-ya);
                z = Math.abs(values[2]-za);

                if((x + y  + z) >30) {
                    Log.i("Cambio SENSORIAL ", "... " + x + " - " + y + " - " + z);
                    unreg();
                    activity.showDialog("TAXI!!", "Es hora de ir a casa, te pido un taxi en tu localización?");
                    reg();
                }
            }
            xa = values[0];
            ya = values[1];
            za = values[2];
            pp=true;

        }
    }





}
