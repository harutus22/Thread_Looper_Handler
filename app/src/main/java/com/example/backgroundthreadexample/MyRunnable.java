package com.example.backgroundthreadexample;

import android.util.Log;

public class MyRunnable implements Runnable {
    private static final String TAG = "MyRunnable";
    private int seconds;

    public MyRunnable(int seconds){
        this.seconds = seconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "startThread: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
