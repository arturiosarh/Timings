package com.example.android.timings;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotifChanel extends Activity {

    private Context context;
    private String CHANNEL_ID;
    private LocalDateTime nowTime;

    public NotifChanel(Context context, String CHANNEL_ID) {
        this.context = context;
        this.CHANNEL_ID = CHANNEL_ID;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void activation() {
        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name = "Уведомления тайминогов";
        String description = "my_channel";
        int importance = NotificationManager.IMPORTANCE_MAX;

        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        mChannel.setName(name);
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mNotificationManager.createNotificationChannel(mChannel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification getNotif(String nameOfTiming, int laps, String startTime) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE);
        nowTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String nowTimeTxt = nowTime.format(dtf);

        Notification notification = new Notification.Builder(context)
                .setContentTitle(nameOfTiming)
                .setContentText("На тайминге " + nameOfTiming + " время закончилось " + laps + " раз(а) с "+ startTime + " до " + nowTimeTxt +" !")
                .setSmallIcon(R.drawable.timer)
                .setContentIntent(pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setOngoing(false)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .build();
        notification.flags = notification.flags | Notification.FLAG_INSISTENT;
        Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        notification.sound = ringUri;
        return notification;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Notification startNotif(String nameOfTiming, int laps) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE);

        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context)
                    .setContentTitle(nameOfTiming)
                    .setContentText("Активно в фоновом режиме.")
                    .setSmallIcon(R.drawable.timer)
                    .setChannelId(CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setOngoing(false)
                    .setAutoCancel(true)
                    .build();
        }
        return notification;
    }
}