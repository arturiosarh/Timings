package com.example.android.timings;

import static android.media.RingtoneManager.*;

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
import androidx.core.app.NotificationCompat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotifChanel extends Activity {

    private Context context;
    private LocalDateTime nowTime;

    public NotifChanel(Context context) {
        this.context = context;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void activation() {
        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name = "Уведомления тайминогов";
        String description = "my_channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel mChannel = new NotificationChannel("my_channel", name, importance);
        mChannel.setName(name);
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mNotificationManager.createNotificationChannel(mChannel);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void activationForegroundChannel() {
        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name = "Фоновое оповещение";
        String description = "foreground_channel";
        int importance = NotificationManager.IMPORTANCE_MIN;

        NotificationChannel mChannel = new NotificationChannel("foreground_channel", name, importance);
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
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        String nowTimeTxt = nowTime.format(dtf1);

        Notification notification = new NotificationCompat.Builder(context,"my_channel")
                .setContentTitle(nameOfTiming)
                .setContentText("На тайминге " + nameOfTiming + " время закончилось " + laps + " раз(а) с "+ startTime + " до " + nowTimeTxt +" !")
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("На тайминге " + nameOfTiming)
                        .addLine("время закончилось " + laps + " раз(а)")
                        .addLine("с "+ startTime)
                        .addLine(" до " + nowTimeTxt +" !"))
                .setSmallIcon(R.drawable.timer)
                .setContentIntent(pendingIntent)
                .setChannelId("my_channel")
                .setOngoing(false)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSound(RingtoneManager.getDefaultUri(TYPE_ALARM))
                .build();
        notification.flags = notification.flags | Notification.FLAG_INSISTENT;
        return notification;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Notification startNotif(String nameOfTiming) {

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE);

        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context)
                    .setContentTitle(nameOfTiming)
                    .setContentText("Работа в фоновом режиме.")
                    .setSmallIcon(R.drawable.timer)
                    .setChannelId("foreground_channel")
                    .setContentIntent(pendingIntent)
                    .setOngoing(false)
                    .setAutoCancel(true)
                    .build();
        }
        return notification;
    }
}