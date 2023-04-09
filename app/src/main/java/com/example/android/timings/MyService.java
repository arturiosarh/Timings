package com.example.android.timings;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;

import androidx.annotation.RequiresApi;

public class MyService extends Service {

    private final Context context = this;
    private static final int NOTIFY_ID = 1;
    private boolean foregroundIsRun = false;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!foregroundIsRun) {
            runAsForeground();
        }
        if (intent.getAction() == null) {
            return START_STICKY;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDestroy() {
        int count = 0;
        for (int i = 0; i < ((App) getApplication()).getTimings().length; i++) {
            if (((App) getApplication()).getTimings()[i].getMyTimer() == null) {
                count++;
            }
        }
        if (count == 50) {
            super.onDestroy();
            foregroundIsRun = false;
        } else {
            foregroundIsRun = false;
            context.startService(new Intent(context, MyService.class));
            runAsForeground();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void runAsForeground(){
        new NotifChanel(context).activationForegroundChannel();
        String nameOfTiming = "Timings";
        startForeground(NOTIFY_ID, new NotifChanel(context).startNotif(nameOfTiming));
        foregroundIsRun = true;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}