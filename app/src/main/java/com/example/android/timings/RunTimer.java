package com.example.android.timings;

import android.app.Activity;
import java.util.Timer;
import java.util.TimerTask;

public class RunTimer extends Activity {
    public void tic (Timer timer, Runnable runnable, long period) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(runnable);
            }
        }, 0, period);
    }
}
