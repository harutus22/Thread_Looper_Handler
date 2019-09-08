package com.example.backgroundthreadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.backgroundthreadexample.MyHandler.TASK_A;
import static com.example.backgroundthreadexample.MyHandler.TASK_B;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button startButton;
    private Handler handler;
    private volatile boolean isStopped = false;
    private MyThread myThread = new MyThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.btnStart);


//        handler = new Handler();
    }

    public void startThread(View view){
//        MyRunnable2 myRunnable = new MyRunnable2(10);
//        new Thread(myRunnable).start();
        myThread.start();
        isStopped = false;
    }

    public void stopThread(View view){
//        isStopped = true;
        myThread.looper.quit();
    }

    public void startTaskA(View view){
        Message message = Message.obtain();
        message.what = TASK_A;
        myThread.handler.sendMessage(message);

//        myThread.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5; i++) {
//                    Log.d(TAG, "run: " + i);
//                    SystemClock.sleep(1000);
//                }
//            }
//        });
    }

    public void startTaskB(View view){
        Message message = Message.obtain();
        message.what = TASK_B;
        myThread.handler.sendMessage(message);
    }

    class MyRunnable2 implements Runnable{
        private int seconds;

        public MyRunnable2(int seconds){
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                if(isStopped){
                    return;
                }
                if(i == 5){
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            startButton.setText("50%");
//                        }
//                    });

//                    startButton.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            startButton.setText("50%");
//                        }
//                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startButton.setText("50%");
                        }
                    });
                }
                try {
                    Thread.sleep(1000);
                    Log.d(TAG, "startThread: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
