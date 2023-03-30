package com.example.android.timings;

import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.Gravity.START;
import static android.view.Gravity.TOP;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

public class Timing extends Activity {

    private final int NOTIFY_ID;               //уведомления
    private final NotifChanel notifChanel;
    private NotificationManager nm;

    private String nameOfTiming;
    private int minutes;
    private int beginMinutes;
    private int hours;
    private final int beginHours;
    private int days;
    private int daysInTimer;
    private int laps;

    private Timer myTimer;
    //private Timer newTimer;
    private LocalTime timer;
    //private LocalTime timer0;
    private String time;
    private LocalDateTime timeOfBegin;
    private String nowTimeBegin;
    private String nowTimeBeginFull;

    private Timer buttonRunner;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private final Context context;
    private LinearLayout linearLayout1;

    private final int idTextView1_1_1_1;
    private final int idTextView1_1_2;
    private final int idButton1_5_1;
    private final int idButton1_5_2;
    private final int idEditText1_1_3;
    private final int idTextView1_1_1_01;
    private TextView textView1_1_1_1;
    private TextView textView1_1_2;
    private EditText editText1_1_3;
    private TextView textView1_1_1_01;
    private Activity activity;

    public Timing(Context context,
                  String nameOfTiming,
                  int minutes,
                  int beginMinutes,
                  int hours,
                  int beginHours,
                  int days,
                  int daysInTimer,
                  int laps,
                  String time,
                  String nowTimeBegin,
                  String nowTimeBeginFull,
                  int NOTIFY_ID,
                  int idTextView1_1_1_1,
                  int idTextView1_1_2,
                  int idButton1_5_1,
                  int idButton1_5_2,
                  int idEditText1_1_3,
                  int idTextView1_1_1_01,
                  NotifChanel notifChanel,
                  Activity activity) {
        this.context = context;
        this.nameOfTiming = nameOfTiming;
        this.minutes = minutes;
        this.beginMinutes = beginMinutes;
        this.hours = hours;
        this.beginHours = beginHours;
        this.days = days;
        this.daysInTimer = daysInTimer;
        this.laps = laps;
        this.time = time;
        this.nowTimeBegin = nowTimeBegin;
        this.nowTimeBeginFull = nowTimeBeginFull;
        this.NOTIFY_ID = NOTIFY_ID;
        this.idTextView1_1_1_1 = idTextView1_1_1_1;
        this.idTextView1_1_2 = idTextView1_1_2;
        this.idButton1_5_1 = idButton1_5_1;
        this.idButton1_5_2 = idButton1_5_2;
        this.idEditText1_1_3 = idEditText1_1_3;
        this.idTextView1_1_1_01 = idTextView1_1_1_01;
        this.notifChanel = notifChanel;
        this.activity = activity;
    }

    public void setBeginTimeOnZero() {
        this.hours = 0;
        this.minutes = 0;
        this.days = 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n", "UnspecifiedImmutableFlag"})
    public LinearLayout newTiming() {
        try {

            TextView textView1_2_2;
            TextView textView1_4_2;

            float dest = context.getResources().getDisplayMetrics().density;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");

            linearLayout1 = new LinearLayout(context);
            LinearLayout.LayoutParams linerLayout1Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1.setLayoutParams(linerLayout1Params);
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1.setBackgroundResource(androidx.cardview.R.color.cardview_dark_background);

            LinearLayout linearLayout1_1 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_1Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_1Params.gravity = CENTER;
            linearLayout1_1.setLayoutParams(linearLayout1_1Params);
            linearLayout1_1Params.setMargins((int) (6 * dest), (int) (-6 * dest), 0, 0);
            linearLayout1_1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(linearLayout1_1);

            LinearLayout linearLayout1_1_1 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_1_1Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            linearLayout1_1_1Params.gravity = CENTER;
            linearLayout1_1_1.setLayoutParams(linearLayout1_1_1Params);
            linearLayout1_1_1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1_1.addView(linearLayout1_1_1);

            LinearLayout linearLayout1_1_02 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_1_02Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_1_02Params.gravity = CENTER;
            linearLayout1_1_02Params.setMargins(0,0,0,0);
            linearLayout1_1_02.setLayoutParams(linearLayout1_1_02Params);
            linearLayout1_1_02.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1_1_1.addView(linearLayout1_1_02);

            textView1_1_1_1 = new TextView(context);
            LinearLayout.LayoutParams textView1_1_1_1Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_1_1_1.setLayoutParams(textView1_1_1_1Params);
            textView1_1_1_1Params.gravity = BOTTOM;
            textView1_1_1_1.setText("0");
            textView1_1_1_1.setTextSize(30);
            textView1_1_1_1.setId(idTextView1_1_1_1);
            linearLayout1_1_02.addView(textView1_1_1_1);

            TextView textView1_1_1_2 = new TextView(context);
            LinearLayout.LayoutParams textView1_1_1_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_1_1_2.setLayoutParams(textView1_1_1_2Params);
            textView1_1_1_2Params.gravity = BOTTOM;
            textView1_1_1_2.setText(" дней");
            textView1_1_1_2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            textView1_1_1_2.setTextSize(15);
            linearLayout1_1_02.addView(textView1_1_1_2);

            textView1_1_2 = new TextView(context);
            LinearLayout.LayoutParams textView1_1_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_1_2Params.gravity = CENTER_VERTICAL;
            textView1_1_2.setLayoutParams(textView1_1_2Params);
            textView1_1_2.setText(dtf.format(LocalTime.of(0,0,0)));
            textView1_1_2.setTextSize(30);
            textView1_1_2.setId(idTextView1_1_2);
            textView1_1_2.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
            linearLayout1_1.addView(textView1_1_2);

            LinearLayout linearLayout1_1_03 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_1_03Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_1_02Params.setMargins(0,0,0,0);
            linearLayout1_1_02.setLayoutParams(linearLayout1_1_03Params);
            linearLayout1_1_02.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1_1.addView(linearLayout1_1_03);

            editText1_1_3 = new EditText(context);
            LinearLayout.LayoutParams editText1_1_3Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            editText1_1_3Params.gravity = CENTER_HORIZONTAL;
            editText1_1_3.setLayoutParams(editText1_1_3Params);
            editText1_1_3.setCursorVisible(false);
            editText1_1_3.setTextSize(15);
            editText1_1_3.setEms(4);
            editText1_1_3.setText(nameOfTiming);
            editText1_1_3.setId(idEditText1_1_3);
                    editText1_1_3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (editText1_1_3.getLineCount() > 1) {
                                editText1_1_3.setText(s.toString().substring(0, before));
                                editText1_1_3.setSelection(before);
                                nameOfTiming = editText1_1_3.getText().toString();
                            }
                            ;
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });
            linearLayout1_1_03.addView(editText1_1_3);

            LinearLayout linearLayout1_1_01 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_1_01Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_1_01Params.gravity = START | BOTTOM;
            linearLayout1_1_01Params.setMargins(0,0,10,0);
            linearLayout1_1_01.setLayoutParams(linearLayout1_1_01Params);
            linearLayout1_1_01.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1_1_03.addView(linearLayout1_1_01);

            textView1_1_1_01 = new TextView(context);
            LinearLayout.LayoutParams textView1_1_1_01Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_1_1_01.setLayoutParams(textView1_1_1_01Params);
            textView1_1_1_01Params.setMargins(0,0,0,0);
            textView1_1_1_01Params.gravity = TOP;
            textView1_1_1_01.setText("" + laps);
            textView1_1_1_01.setTextSize(25);
            textView1_1_1_01.setId(idTextView1_1_1_01);
            linearLayout1_1_01.addView(textView1_1_1_01);

            View view1_1_1_02 = new View(context);
            LinearLayout.LayoutParams view1_1_1_02Params = new LinearLayout.LayoutParams
                    ((int) (11 * dest), (int) (11 * dest));
            view1_1_1_02.setLayoutParams(view1_1_1_02Params);
            view1_1_1_02Params.gravity = BOTTOM;
            view1_1_1_02Params.setMargins(0, 0, 0, 30);
            view1_1_1_02.setBackgroundResource(R.drawable. continue_icon);
            linearLayout1_1_01.addView(view1_1_1_02);

            LinearLayout linearLayout1_15 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_15Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_15.setLayoutParams(linearLayout1_15Params);
            linearLayout1_15.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(linearLayout1_15);

            Button button1_15_1 = new Button(context);
            LinearLayout.LayoutParams button1_15_1Params = new LinearLayout.LayoutParams
                    ((int) (50 * dest), (int) (50 * dest));
            button1_15_1.setLayoutParams(button1_15_1Params);
            button1_15_1.setBackgroundResource(R.drawable.arrow_up);

            linearLayout1_15.addView(button1_15_1);

            LinearLayout linearLayout1_15_2 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_15_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_15_2Params.gravity = CENTER;
            linearLayout1_15_2.setLayoutParams(linearLayout1_15_2Params);
            linearLayout1_15_2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout1_15.addView(linearLayout1_15_2);

            TextView textView1_15_2 = new TextView(context);
            LinearLayout.LayoutParams textView1_15_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_15_2Params.gravity = CENTER;
            textView1_15_2Params.setMargins(10,0,0,0);
            textView1_15_2.setLayoutParams(textView1_15_2Params);
            textView1_15_2.setText(NumberFormat.getNumberInstance().format(days));
            textView1_15_2.setTextSize(30);
            linearLayout1_15_2.addView(textView1_15_2);

            TextView textView1_15_1 = new TextView(context);
            LinearLayout.LayoutParams textView1_15_1Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_15_1Params.gravity = BOTTOM;
            textView1_15_1.setLayoutParams(textView1_15_1Params);
            textView1_15_1.setText("д.");
            textView1_15_1.setTextSize(15);
            linearLayout1_15_2.addView(textView1_15_1);

            Button button1_15_3 = new Button(context);
            LinearLayout.LayoutParams button1_15_3Params = new LinearLayout.LayoutParams
                    ((int) (50 * dest), (int) (50 * dest));
            button1_15_3Params.setMargins(0, (int) (6 * dest), 0, 0);
            button1_15_3.setLayoutParams(button1_15_3Params);
            button1_15_3.setBackgroundResource(R.drawable.arrow_up);
            button1_15_3.setRotation(180);
            linearLayout1_15.addView(button1_15_3);

            LinearLayout linearLayout1_2 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_2.setLayoutParams(linearLayout1_2Params);
            linearLayout1_2.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(linearLayout1_2);

            Button button1_2_1 = new Button(context);
            LinearLayout.LayoutParams button1_2_1Params = new LinearLayout.LayoutParams
                    ((int) (50 * dest), (int) (50 * dest));
            button1_2_1.setLayoutParams(button1_2_1Params);
            button1_2_1.setBackgroundResource(R.drawable.arrow_up);

            linearLayout1_2.addView(button1_2_1);

            textView1_2_2 = new TextView(context);
            LinearLayout.LayoutParams textView1_2_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_2_2Params.gravity = CENTER;
            textView1_2_2.setLayoutParams(textView1_2_2Params);
            textView1_2_2.setText(NumberFormat.getNumberInstance().format(hours));
            textView1_2_2.setTextSize(30);
            linearLayout1_2.addView(textView1_2_2);

            Button button1_2_3 = new Button(context);
            LinearLayout.LayoutParams button1_2_3Params = new LinearLayout.LayoutParams
                    ((int) (50 * dest), (int) (50 * dest));
            button1_2_3Params.setMargins(0, (int) (6 * dest), 0, 0);
            button1_2_3.setLayoutParams(button1_2_3Params);
            button1_2_3.setBackgroundResource(R.drawable.arrow_up);
            button1_2_3.setRotation(180);
            linearLayout1_2.addView(button1_2_3);

            TextView textView1_3 = new TextView(context);
            LinearLayout.LayoutParams textView1_3Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_3Params.gravity = CENTER_VERTICAL;
            textView1_3.setLayoutParams(textView1_3Params);
            textView1_3.setText(":");
            textView1_3.setTextSize(30);
            textView1_3Params.setMargins(0, (int) (-4 * dest), 0, 0);
            linearLayout1.addView(textView1_3);

            LinearLayout linearLayout1_4 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout1_4Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_4.setLayoutParams(linearLayout1_4Params);
            linearLayout1_4.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(linearLayout1_4);

            Button button1_4_1 = new Button(context);
            LinearLayout.LayoutParams button1_4_1Params = new LinearLayout.LayoutParams
                    ((int) (50 * dest), (int) (50 * dest));
            button1_4_1.setLayoutParams(button1_4_1Params);
            button1_4_1.setBackgroundResource(R.drawable.arrow_up);

            linearLayout1_4.addView(button1_4_1);

            textView1_4_2 = new TextView(context);
            LinearLayout.LayoutParams textView1_4_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView1_4_2Params.gravity = CENTER;
            textView1_4_2.setLayoutParams(textView1_4_2Params);
            textView1_4_2.setText(NumberFormat.getNumberInstance().format(minutes));
            textView1_4_2.setTextSize(30);
            linearLayout1_4.addView(textView1_4_2);

            Button button1_4_3 = new Button(context);
            LinearLayout.LayoutParams button1_4_3Params = new LinearLayout.LayoutParams
                    ((int) (50 * dest), (int) (50 * dest));
            button1_4_3Params.setMargins(0, (int) (6 * dest), 0, 0);
            button1_4_3.setLayoutParams(button1_4_3Params);
            button1_4_3.setBackgroundResource(R.drawable.arrow_up);
            button1_4_3.setRotation(180);

            linearLayout1_4.addView(button1_4_3);

            LinearLayout linearLayout1_5 = new LinearLayout(context);
            LinearLayout.LayoutParams layoutLayout1_5Params = new  LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1_5.setLayoutParams(layoutLayout1_5Params);
            linearLayout1_5.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.addView(linearLayout1_5);

            Button button1_5_1 = new Button(context);
            LinearLayout.LayoutParams button1_5_1Params = new LinearLayout.LayoutParams
                    ((int) (43 * dest), (int) (43 * dest));
            button1_5_1Params.setMargins(10,81, 0, 0);
            button1_5_1Params.gravity = CENTER_VERTICAL;
            button1_5_1.setLayoutParams(button1_5_1Params);
            button1_5_1.setId(idButton1_5_1);
            button1_5_1.setBackgroundResource(R.drawable.play1);
            button1_5_1.setOnClickListener(v -> {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    int permission1Status = ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS);
                    if (permission1Status != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 22);
                    }
                }

                int permission2Status = ContextCompat.checkSelfPermission(context, android.Manifest.permission.RECEIVE_BOOT_COMPLETED);
                if (permission2Status != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[] {android.Manifest.permission.RECEIVE_BOOT_COMPLETED},23);
                }

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                    int permission3Status = ContextCompat.checkSelfPermission(context, android.Manifest.permission.SCHEDULE_EXACT_ALARM);
                    if (permission3Status != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SCHEDULE_EXACT_ALARM}, 24);
                    }
                }
                timeOfBegin = LocalDateTime.now();
                nowTimeBeginFull = dtf1.format(timeOfBegin);
                nowTimeBegin = dtf.format(timeOfBegin);
                if (myTimer != null) {
                    myTimer.cancel();
                }
                nameOfTiming = editText1_1_3.getText().toString();
                beginMinutes = minutes;
                timer = LocalTime.of(beginHours, beginMinutes, 0);
                daysInTimer = days;

                LocalDateTime localDateTime;
                localDateTime = timeOfBegin.plusDays(daysInTimer).plusHours(beginHours).plusMinutes(beginMinutes);
                //LocalDateTime localDateTime1 = timeOfBegin.plusSeconds(30);
                Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
                Duration duration01 = Duration.between(timeOfBegin, localDateTime); //промежуток времени
                //Duration duration02 = Duration.between(timeOfBegin, localDateTime1); //ожидание намерения
                /*Calendar notifyTime = new GregorianCalendar();
                notifyTime.set(Calendar.DAY_OF_MONTH, daysInTimer);
                notifyTime.set(Calendar.HOUR_OF_DAY, beginHours);
                notifyTime.set(Calendar.MINUTE, beginMinutes);*/

                int beginDays = days;
                if (days > 0 && timer.equals(LocalTime.of(0, 0, 0))) {
                    daysInTimer--;
                }
                if (days > 0 || hours > 0 || minutes > 0) {
                    Toast.makeText(context, nameOfTiming + ": установлен на " + days + " дней " + hours + " ч. и " + minutes + " м." +
                                    " в " + nowTimeBeginFull,
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MyService.class);
                    context.startService(intent);  // запускаем фоновый сервис с уведомлением

                    Intent intent01 = new Intent(context, MyReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(context, 135, intent01, PendingIntent.FLAG_IMMUTABLE);
                    alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, instant.toEpochMilli(), pendingIntent);

                    laps = 0;
                    textView1_1_1_01.setText(NumberFormat.getNumberInstance().format(laps));
                    myTimer = new Timer();  //добавление таймера
                    new RunTimer().tic(myTimer, () -> {
                        nameOfTiming = editText1_1_3.getText().toString();

                        LocalDateTime localDateTimeNow = LocalDateTime.now();
                        LocalDateTime nTBF = LocalDateTime.parse(nowTimeBeginFull,dtf1);
                        Duration duration = Duration.between(nTBF, localDateTimeNow);
                        long secondsPassed = duration.getSeconds();


                        int restSeconds = (int) (60 - (secondsPassed % 60));
                        if (restSeconds == 60) {
                            restSeconds = 0;
                        }
                        int beginSeconds = ((((beginDays * 24) + beginHours) * 60) + beginMinutes) * 60;

                        int daysInTimer0 = (int) ((((beginSeconds - (secondsPassed % beginSeconds)) / 60) / 60 / 24));

                        int restHours = (int) ((((beginSeconds - (secondsPassed % beginSeconds)) / 60) / 60));
                        if (restHours > 23) {
                            restHours = restHours - (daysInTimer * 24);
                        }

                        int restMinutes = (int) (((beginSeconds - ((secondsPassed % beginSeconds))) / 60));
                        if (restMinutes > 59) {
                            restMinutes = restMinutes - (((daysInTimer * 24) + restHours) * 60);
                        }
                        if (restHours == 24) {
                            restHours = 23;
                            restMinutes = 59;
                        }

                        LocalTime timer0 = LocalTime.of(restHours, restMinutes, restSeconds);
                        Duration timingDuration = Duration.between(timer0, timer);
                        if (timingDuration.getSeconds() > 0) {
                            timer = timer0;
                            daysInTimer = daysInTimer0;
                        }

                        timer = timer.minusSeconds(1);
                        time = dtf.format(timer);
                        textView1_1_2.setText(time);
                        textView1_1_1_1.setText(NumberFormat.getNumberInstance()
                                .format(daysInTimer));
                        if (timer.equals(LocalTime.of(0, 0, 0))) {
                            if (daysInTimer > 0) {
                                daysInTimer--;
                            } else {
                                daysInTimer = days;
                                timer = LocalTime.of(beginHours, beginMinutes, 0);
                                laps++;
                                LocalDateTime localDateTime2 = LocalDateTime.now();
                                LocalDateTime localDateTime1 = localDateTime2.plusDays(daysInTimer).plusHours(beginHours).plusMinutes(beginMinutes);
                                Instant instant1 = localDateTime1.toInstant(ZoneOffset.UTC);
                                alarmManager.setExact(AlarmManager.RTC_WAKEUP, instant1.toEpochMilli(), pendingIntent);
                                textView1_1_1_01.setText(NumberFormat.getNumberInstance()
                                        .format(laps));
                                nm = (NotificationManager) context.
                                        getSystemService(MyService.NOTIFICATION_SERVICE);
                                nm.notify(NOTIFY_ID, notifChanel.getNotif(nameOfTiming, laps, nowTimeBeginFull));
                            }
                        }
                    }, 1000);
                } else {
                    timer = LocalTime.of(0, 0, 0);
                    time = dtf.format(timer);
                    textView1_1_2.setText(time);
                    daysInTimer = 0;
                    textView1_1_1_1.setText(NumberFormat.getNumberInstance().format(daysInTimer));
                }
            });
            linearLayout1_5.addView(button1_5_1);

            Button button1_5_2 = new Button(context);
            LinearLayout.LayoutParams button1_5_2Params = new LinearLayout.LayoutParams
                    ((int) (35 * dest), (int) (35 * dest));
            button1_5_2Params.setMargins(20, 10, 0, 0);
            button1_5_2Params.gravity = CENTER_VERTICAL;
            button1_5_2.setLayoutParams(button1_5_2Params);
            button1_5_2.setId(idButton1_5_2);
            button1_5_2.setBackgroundResource(R.drawable.stop1);
            button1_5_2.setOnClickListener(v -> {
                Toast.makeText(context, nameOfTiming + ": " + "выключен",
                        Toast.LENGTH_SHORT).show();
                if (myTimer != null) {
                    myTimer.cancel();
                    alarmManager.cancel(pendingIntent);
                }
                timer = LocalTime.of(0, 0, 0);
                time = dtf.format(timer);
                laps = 0;
                textView1_1_1_01.setText(NumberFormat.getNumberInstance().format(laps));
                textView1_1_2.setText(time);
                daysInTimer = 0;
                textView1_1_1_1.setText(NumberFormat.getNumberInstance().format(daysInTimer));
            });
            linearLayout1_5.addView(button1_5_2);

            button1_15_1.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleX(0.90f).scaleY(0.90f).setDuration(1);
                    buttonRunner = new Timer();
                    new RunTimer().tic(buttonRunner, () -> {
                        days++;
                        textView1_15_2.setText(NumberFormat.getNumberInstance().format(days));
                        textView1_2_2.setText(NumberFormat.getNumberInstance().format(hours));
                        textView1_4_2.setText(NumberFormat.getNumberInstance().format(minutes));
                    }, 150);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                }
                return true;
            });

            button1_15_3.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleX(0.90f).scaleY(0.90f).setDuration(1);
                    buttonRunner = new Timer();
                    new RunTimer().tic(buttonRunner, () -> {
                        if (days > 0) {
                            days--;
                        }
                        textView1_15_2.setText(NumberFormat.getNumberInstance().format(days));
                        textView1_2_2.setText(NumberFormat.getNumberInstance().format(hours));
                        textView1_4_2.setText(NumberFormat.getNumberInstance().format(minutes));
                    }, 150);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                }
                return true;
            });

            button1_2_1.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleX(0.90f).scaleY(0.90f).setDuration(1);
                    buttonRunner = new Timer();
                    new RunTimer().tic(buttonRunner, () -> {
                       if (hours >= 23) {
                           hours = 0;
                           days++;
                       } else {
                           hours++;
                       }
                        textView1_15_2.setText(NumberFormat.getNumberInstance().format(days));
                        textView1_2_2.setText(NumberFormat.getNumberInstance().format(hours));
                        textView1_4_2.setText(NumberFormat.getNumberInstance().format(minutes));
                    }, 150);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                }
                return true;
            });

            button1_2_3.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleX(0.90f).scaleY(0.90f).setDuration(1);
                    buttonRunner = new Timer();
                    new RunTimer().tic(buttonRunner, () -> {
                        if (hours > 0) {
                            hours--;
                        } else {
                            if (days > 0) {
                                hours = 23;
                                days--;
                            }
                        }
                        textView1_15_2.setText(NumberFormat.getNumberInstance().format(days));
                        textView1_2_2.setText(NumberFormat.getNumberInstance().format(hours));
                        textView1_4_2.setText(NumberFormat.getNumberInstance().format(minutes));
                    }, 150);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                }
                return true;
            });

            button1_4_1.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleX(0.90f).scaleY(0.90f).setDuration(1);
                    buttonRunner = new Timer();
                    new RunTimer().tic(buttonRunner, () -> {
                        if (minutes >= 59) {
                            minutes = 0;
                            if (hours >= 23) {
                                hours = 0;
                                days++;
                            } else {
                                hours++;
                            }
                        } else {
                            minutes++;
                        }
                        textView1_15_2.setText(NumberFormat.getNumberInstance().format(days));
                        textView1_4_2.setText(NumberFormat.getNumberInstance().format(minutes));
                        textView1_2_2.setText(NumberFormat.getNumberInstance().format(hours));
                    }, 150);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                }
                return true;
            });

            button1_4_3.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.animate().scaleX(0.90f).scaleY(0.90f).setDuration(1);
                    buttonRunner = new Timer();
                    new RunTimer().tic(buttonRunner, () -> {
                        if (minutes > 0) {
                            minutes--;
                        } else {
                            if (hours > 0) {
                                minutes = 59;
                                hours--;
                            } else {
                                if (days > 0) {
                                    hours = 23;
                                    days--;
                                }
                            }
                        }
                        textView1_15_2.setText(NumberFormat.getNumberInstance().format(days));
                        textView1_4_2.setText(NumberFormat.getNumberInstance().format(minutes));
                        textView1_2_2.setText(NumberFormat.getNumberInstance().format(hours));
                    }, 150);
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                } else if (event.getAction() == MotionEvent.ACTION_CANCEL){
                    buttonRunner.cancel();
                    v.animate().scaleX(1f).scaleY(1f).setDuration(1);
                }
                return true;
            });

            return linearLayout1;

        } catch (Exception exception) {
            Toast.makeText(context, exception.toString(),
                    Toast.LENGTH_SHORT).show();
            Log.e("ERROR", "Exception", exception);
            return null;
        }
    }

    public Timer getMyTimer() {
        return myTimer;
    }
    public void cancelMyTimer() {
        this.myTimer.cancel();
    }
    public String getNameOfTiming () {
        return nameOfTiming;
    }
    public int getMinutes () {
        return minutes;
    }
    public int getBeginMinutes () {
        return beginMinutes;
    }
    public int getHours () {
        return hours;
    }
    public int getBeginHours() {
        return beginHours;
    }
    public int getDaysInTimer() {
        return daysInTimer;
    }
    public int getDays() {
        return days;
    }
    public int getLaps() {
        return laps;
    }
    public String getTime() {
        return time;
    }
    public String getNowTimeBegin() {
        return nowTimeBegin;
    }

    public String getNowTimeBeginFull() {
        return nowTimeBeginFull;
    }
    public LinearLayout removeLinerLayout1() {
        return linearLayout1;
    }
}