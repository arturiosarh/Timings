package com.example.android.timings;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;

public class MyService extends Service {

    private Context context = this;
    private static final int NOTIFY_ID = 1;
    private String nameOfTiming = "Timings";


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        runAsForeground();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void runAsForeground(){
        new NotifChanel(context).activationForegroundChannel();
        startForeground(NOTIFY_ID, new NotifChanel(context).startNotif(nameOfTiming));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}