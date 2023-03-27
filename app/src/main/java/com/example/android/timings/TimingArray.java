package com.example.android.timings;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimingArray extends Activity {

    private Context context;
    private SharedPreferences sharedPreferences;



    public TimingArray(Context context, SharedPreferences sharedPreferences){
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Timing[] setArrayListTiming(Timing[] arrayListTiming, NotifChanel[] notifChanels){
        for (int i = 0; i < arrayListTiming.length; i++) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dtff = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            arrayListTiming[i] = new Timing (context,
                    sharedPreferences.getString("nameOfTiming" + i,"№ " + (i + 1)),
                    sharedPreferences.getInt("minutes" + i, 0),
                    sharedPreferences.getInt("beginMinutes" + i, 0),
                    sharedPreferences.getInt("hours" + i, 0),
                    sharedPreferences.getInt("beginHours" + i, 0),
                    sharedPreferences.getInt("day" + i, 0),
                    sharedPreferences.getInt("daysInTimer" + i, 0),
                    sharedPreferences.getInt("laps" + i, 0),
                    i,
                    sharedPreferences.getString("timerString" + i, dtf.format(LocalTime.of(0, 0, 0))),
                    sharedPreferences.getString("nowTimeBegin" + i, dtf.format(LocalTime.now())),
                    sharedPreferences.getString("nowTimeBeginFull" + i, dtff.format(LocalDateTime.now())),
                    i + 2,
                    View.generateViewId(),
                    View.generateViewId(),
                    View.generateViewId(),
                    View.generateViewId(),
                    View.generateViewId(),
                    View.generateViewId(),
                    notifChanels[i]);
        }
        return arrayListTiming;
    }
}