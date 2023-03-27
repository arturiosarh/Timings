package com.example.android.timings;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class MyService extends IntentService {

    private Context context = this;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "my_channel_0";
    private int laps = 0;
    private String nameOfTiming = "Тайминги включены";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }


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
        new NotifChanel(context,CHANNEL_ID).activation();
        startForeground(NOTIFY_ID, new NotifChanel(context,CHANNEL_ID).startNotif(nameOfTiming,laps));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}