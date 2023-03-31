package com.example.android.timings;

import static android.view.Gravity.BOTTOM;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import android.Manifest;

import com.my.target.ads.MyTargetView;

import java.text.NumberFormat;

public class StartButtons extends ViewModel {

    private static final int BUTTON9 = 12;
    private static final int BUTTON9_1 = 15;
    private static final int LINERLAY9 = 14;

    private TextView textView9_2;
    private LinearLayout linearLayout;
    private ScrollView scrollView;

    private int altNumber;
    private final String altNumberKey = "altNumber";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private Activity activity;

    public StartButtons(Context context, Activity activity, SharedPreferences sharedPreferences){
        this.context = context;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ScrollView startTiming(Timing[] arrayListTiming, MyTargetView myTargetView, MyTargetView myTargetView0) {  //главный экран
        try {
            editor = sharedPreferences.edit();
            altNumber = sharedPreferences.getInt(altNumberKey, 0);
            scrollView = new ScrollView(context);
            ScrollView.LayoutParams scrollViewParams = new ScrollView.LayoutParams
                    (ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT);
            scrollView.setLayoutParams(scrollViewParams);

            linearLayout = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(linearLayoutParams);
            scrollView.addView(linearLayout);

            Button button9 = new Button(context);   //кнопка добавления тайминга
            button9.setId(BUTTON9);
            LinearLayout.LayoutParams button9Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button9.setLayoutParams(button9Params);
            button9.setText("+");
            button9.setTextSize(20);

            LinearLayout linearLayout11 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout11Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            linearLayout11.setLayoutParams(linearLayout11Params);
            linearLayout11.setOrientation(LinearLayout.VERTICAL);

            button9.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {   //добавить таймер
                    try {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                            int permission1Status = ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS);
                            if (permission1Status != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 22);
                            }
                        }

                        int permission2Status = ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_BOOT_COMPLETED);
                        if (permission2Status != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.RECEIVE_BOOT_COMPLETED},23);
                        }

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                            int permission3Status = ContextCompat.checkSelfPermission(context, Manifest.permission.SCHEDULE_EXACT_ALARM);
                            if (permission3Status != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SCHEDULE_EXACT_ALARM}, 24);
                            }
                        }

                        int permission4Status = ContextCompat.checkSelfPermission(context, Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                        if (permission4Status != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}, 25);
                        }

                        int permission5Status = ContextCompat.checkSelfPermission(context, Manifest.permission.WAKE_LOCK);
                        if (permission5Status != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WAKE_LOCK}, 26);
                        }

                        if (altNumber < arrayListTiming.length) {
                            linearLayout11.addView(arrayListTiming[altNumber].newTiming());
                            altNumber++;
                            editor.putInt(altNumberKey, altNumber);
                            editor.apply();
                            textView9_2.setText(NumberFormat.getNumberInstance().format(altNumber));
                        } else altNumber = arrayListTiming.length;
                    } catch (Exception exception) {
                        Toast.makeText(context, exception.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.e("ERROR", "Exception", exception);
                    }
                }
            });

            LinearLayout linearLayout9 = new LinearLayout(context);
            linearLayout9.setId(LINERLAY9);
            LinearLayout.LayoutParams linearLayout9Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout9.setLayoutParams(linearLayout9Params);
            linearLayout9.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout9.addView(button9);

            Button button9_1 = new Button(context);
            button9_1.setId(BUTTON9_1);
            LinearLayout.LayoutParams button9_1Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button9_1.setLayoutParams(button9_1Params);
            button9_1.setText("-");
            button9_1.setTextSize(20);
            button9_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //убрать таймер
                    try {
                        if (altNumber > 0) {
                            altNumber--;
                            editor.putInt(altNumberKey, altNumber);
                            editor.apply();
                            textView9_2.setText(NumberFormat.getNumberInstance().format(altNumber));
                        } else {
                            altNumber = 0;
                        }
                        if (arrayListTiming[altNumber].getMyTimer() != null)
                            arrayListTiming[altNumber].cancelMyTimer();
                        arrayListTiming[altNumber].setBeginTimeOnZero();
                        linearLayout11.removeView(arrayListTiming[altNumber].removeLinerLayout1());
                    } catch (Exception exception) {
                        Toast.makeText(context, exception.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.e("ERROR", "Exception", exception);
                    }
                }
            });
            linearLayout9.addView(button9_1);

            textView9_2 = new TextView(context);
            LinearLayout.LayoutParams textView9_2Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView9_2Params.setMargins(70, 0, 0, 0);
            textView9_2.setLayoutParams(textView9_2Params);
            textView9_2.setText(NumberFormat.getNumberInstance().format(altNumber));
            textView9_2.setTextSize(20);
            linearLayout9.addView(textView9_2);

            linearLayout.addView(linearLayout9);

            LinearLayout linearLayout10 = new LinearLayout(context);
            LinearLayout.LayoutParams linearLayout10Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout10.setLayoutParams(linearLayout10Params);
            linearLayout10.setOrientation(LinearLayout.HORIZONTAL);

            myTargetView = new MyTargetView(context);
            myTargetView.setSlotId(1235508);
            LinearLayout.LayoutParams myTargetViewParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            myTargetViewParams.gravity = BOTTOM;
            myTargetView.setLayoutParams(myTargetViewParams);
            myTargetView.setListener(new MyTargetView.MyTargetViewListener() {
                @Override
                public void onLoad(@NonNull MyTargetView myTargetView) {
                    linearLayout10.addView(myTargetView);
                }

                @Override
                public void onNoAd(@NonNull String s, @NonNull MyTargetView myTargetView) {

                }

                @Override
                public void onShow(@NonNull MyTargetView myTargetView) {

                }

                @Override
                public void onClick(@NonNull MyTargetView myTargetView) {

                }
            });

            myTargetView.load();


            myTargetView0 = new MyTargetView(context);
            myTargetView0.setSlotId(1237382);
            LinearLayout.LayoutParams myTargetView0Params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            myTargetView0Params.gravity = BOTTOM;
            myTargetView0.setLayoutParams(myTargetViewParams);
            myTargetView0.setListener(new MyTargetView.MyTargetViewListener() {
                @Override
                public void onLoad(@NonNull MyTargetView myTargetView) {
                    linearLayout.addView(myTargetView);
                }

                @Override
                public void onNoAd(@NonNull String s, @NonNull MyTargetView myTargetView) {

                }

                @Override
                public void onShow(@NonNull MyTargetView myTargetView) {

                }

                @Override
                public void onClick(@NonNull MyTargetView myTargetView) {

                }
            });
            myTargetView0.load();

            linearLayout.addView(linearLayout10);

            for (int i = 0; i < altNumber; i++) {
                linearLayout11.addView(arrayListTiming[i].newTiming());
            }

            linearLayout.addView(linearLayout11);
            return scrollView;

        } catch (Exception exception) {
            Toast.makeText(context, exception.toString(),
                    Toast.LENGTH_SHORT).show();
            Log.e("ERROR", "Exception", exception);
            return null;
        }
    }
}
