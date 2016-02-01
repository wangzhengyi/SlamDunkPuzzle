package com.android.slamdunk.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;

/**
 * Created by wzy on 16-1-31.
 */
public class ShakeDetector implements SensorEventListener{
    private static final int UPDATE_INTERVAL_TIME = 200;

    private Context mContext;
    private SensorManager mSensorManager;
    private ArrayList<OnShakeListener> shakeListeners;

    private int shakeThreshold = 800;
    private boolean stopShake = false;
    private long mLastUpdateTime;
    private float mLastX;
    private float mLastY;
    private float mLastZ;

    public ShakeDetector(Context context, int shakeThreshold) {
        this.mContext = context;
        this.shakeThreshold = shakeThreshold;
        this.mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        this.shakeListeners = new ArrayList<>();
    }

    public boolean start() {
        if (mSensorManager == null) {
            throw new UnsupportedOperationException();
        }
        Sensor localSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (localSensor == null) {
            throw new UnsupportedOperationException();
        }
        mSensorManager.unregisterListener(this);
        boolean bool = mSensorManager.registerListener(this, localSensor, 1);
        stopShake = true;
        return bool;
    }

    public void stop() {
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
        stopShake = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentUpdateTime = System.currentTimeMillis();
        long timeInterval = currentUpdateTime - mLastUpdateTime;
        if (timeInterval < UPDATE_INTERVAL_TIME) return;

        float dx, dy, dz;
        double distance;

        do {

            mLastUpdateTime = currentUpdateTime;

            // 获取x、y、z的坐标
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // 获取距离
            dx = x - mLastX;
            dy = y - mLastY;
            dz = z - mLastZ;

            // 将现在的坐标变成last坐标
            mLastX = x;
            mLastY = y;
            mLastZ = z;

            distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        } while (Math.sqrt(distance) / (timeInterval * 10000.0f) < shakeThreshold || !this.stopShake)
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public interface OnShakeListener {
        void onShake();
    }
}
