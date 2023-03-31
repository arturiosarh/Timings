package com.example.android.timings;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.my.target.ads.MyTargetView;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private final int numberOfTimings = 50;    //объем массива таймингов
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TimingArray timingArray;
    private NotifChanel[] notifChanels;
    private MyTargetView myTargetView1;
    private MyTargetView myTargetView2;
    private Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            init(); // обьявление контекста

        } catch (Exception exception) {
            Toast.makeText(context, exception.toString(),
                    Toast.LENGTH_SHORT).show();
            Log.e("ERROR", "Exception", exception);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        try {
            setContentView(new StartButtons(context, activity, sharedPreferences)
                    .startTiming(((App) getApplication()).getTimings(), myTargetView1, myTargetView2));       // заполнение массива таймингов
        } catch (Exception exception) {
            Toast.makeText(context, exception.toString(),
                    Toast.LENGTH_SHORT).show();
            Log.e("ERROR", "Exception", exception);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < ((App) getApplication()).getTimings().length; i++) {
            editor.putString("nameOfTiming" + i, ((App) getApplication()).getTimings()[i].getNameOfTiming());
            editor.putInt("minutes" + i, ((App) getApplication()).getTimings()[i].getMinutes());
            editor.putInt("beginMinutes" + i, ((App) getApplication()).getTimings()[i].getBeginMinutes());
            editor.putInt("hours" + i, ((App) getApplication()).getTimings()[i].getHours());
            editor.putInt("beginHours" + i, ((App) getApplication()).getTimings()[i].getBeginHours());
            editor.putInt("day" + i, ((App) getApplication()).getTimings()[i].getDays());
            editor.putInt("daysInTimer" + i, ((App) getApplication()).getTimings()[i].getDaysInTimer());
            editor.putInt("laps" + i, ((App) getApplication()).getTimings()[i].getLaps());
            editor.putString("timerString" + i, ((App) getApplication()).getTimings()[i].getTime());
            editor.putString("nowTimeBegin" + i, ((App) getApplication()).getTimings()[i].getNowTimeBegin());
            editor.putString("nowTimeBeginFull" + i, ((App) getApplication()).getTimings()[i].getNowTimeBeginFull());
            editor.apply();
        }
        int count = 0;
        for (int i = 0; i < ((App) getApplication()).getTimings().length; i++) {
            if (((App) getApplication()).getTimings()[i].getMyTimer() == null) {
                count++;
            }
        }
        if (count == 50) {
            context.stopService(new Intent(context,MyService.class));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStop() {
        super.onStop();
        for (int i = 0; i < ((App) getApplication()).getTimings().length; i++) {
            editor.putString("nameOfTiming" + i, ((App) getApplication()).getTimings()[i].getNameOfTiming());
            editor.putInt("minutes" + i, ((App) getApplication()).getTimings()[i].getMinutes());
            editor.putInt("beginMinutes" + i, ((App) getApplication()).getTimings()[i].getBeginMinutes());
            editor.putInt("hours" + i, ((App) getApplication()).getTimings()[i].getHours());
            editor.putInt("beginHours" + i, ((App) getApplication()).getTimings()[i].getBeginHours());
            editor.putInt("day" + i, ((App) getApplication()).getTimings()[i].getDays());
            editor.putInt("daysInTimer" + i, ((App) getApplication()).getTimings()[i].getDaysInTimer());
            editor.putInt("laps" + i, ((App) getApplication()).getTimings()[i].getLaps());
            editor.putString("timerString" + i, ((App) getApplication()).getTimings()[i].getTime());
            editor.putString("nowTimeBegin" + i, ((App) getApplication()).getTimings()[i].getNowTimeBegin());
            editor.putString("nowTimeBeginFull" + i, ((App) getApplication()).getTimings()[i].getNowTimeBeginFull());
            editor.apply();
        }
        int count = 0;
        for (int i = 0; i < ((App) getApplication()).getTimings().length; i++) {
            if (((App) getApplication()).getTimings()[i].getMyTimer() == null) {
                count++;
            }
        }
        if (count == 50) {
            context.stopService(new Intent(context,MyService.class));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < ((App) getApplication()).getTimings().length; i++) {
            editor.putString("nameOfTiming" + i, ((App) getApplication()).getTimings()[i].getNameOfTiming());
            editor.putInt("minutes" + i, ((App) getApplication()).getTimings()[i].getMinutes());
            editor.putInt("beginMinutes" + i, ((App) getApplication()).getTimings()[i].getBeginMinutes());
            editor.putInt("hours" + i, ((App) getApplication()).getTimings()[i].getHours());
            editor.putInt("beginHours" + i, ((App) getApplication()).getTimings()[i].getBeginHours());
            editor.putInt("day" + i, ((App) getApplication()).getTimings()[i].getDays());
            editor.putInt("daysInTimer" + i, ((App) getApplication()).getTimings()[i].getDaysInTimer());
            editor.putInt("laps" + i, ((App) getApplication()).getTimings()[i].getLaps());
            editor.putString("timerString" + i, ((App) getApplication()).getTimings()[i].getTime());
            editor.putString("nowTimeBegin" + i, ((App) getApplication()).getTimings()[i].getNowTimeBegin());
            editor.putString("nowTimeBeginFull" + i, ((App) getApplication()).getTimings()[i].getNowTimeBeginFull());
            editor.apply();
        }
        int count = 0;
        for (int i = 0; i < ((App) getApplication()).getTimings().length; i++) {
            if (((App) getApplication()).getTimings()[i].getMyTimer() == null) {
                count++;
            }
        }
        if (count == 50) {
            context.stopService(new Intent(context,MyService.class));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init() {
        context = this;
        sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        MyReceiver receiver = new MyReceiver();
        notifChanels = new NotifChanel[numberOfTimings];
        activity = this;
        new NotifChanel(context).activation();
        for (int i = 0; i < numberOfTimings; i ++) {
            notifChanels[i] = new NotifChanel(context);
        }
        if (((App) getApplication()).getTimings() == null) {
            ((App) getApplication()).setTimings(new Timing[numberOfTimings]);
            timingArray = new TimingArray(context, sharedPreferences, activity);
            ((App) getApplication()).setTimings(timingArray.setArrayListTiming(
                    ((App) getApplication()).getTimings(), notifChanels));
        }
    }
}