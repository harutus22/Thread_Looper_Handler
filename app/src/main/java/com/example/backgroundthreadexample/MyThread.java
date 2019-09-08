package com.example.backgroundthreadexample;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

public class MyThread extends Thread {
    private static final String TAG = "MyThread";
    public Handler handler;
    public Looper looper;

    @Override
    public void run() {
        Looper.prepare();

        looper = Looper.myLooper();

        handler = new MyHandler();

        Looper.loop();
//        for (int i = 0; i < 5; i++) {
//            Log.d(TAG, "startThread: " + i);
//            SystemClock.sleep(1000);
//        }

        Log.d(TAG, "End of Thread");
    }
}
