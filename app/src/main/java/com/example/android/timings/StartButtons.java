package com.example.android.timings;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.my.target.ads.MyTargetView;

import java.text.NumberFormat;

public class StartButtons extends ViewModel {

    private static final int BUTTON9 = 12;
    private static final int BUTTON9_1 = 15;
    private static final int LINERLAY9 = 14;

    private TextView textView9_2;
    private LinearLayout linearLayout;
    private ScrollView scrollView;
    private MyTargetView myTargetView;

    private int altNumber;
    private final String altNumberKey = "altNumber";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public StartButtons(Context context, SharedPreferences sharedPreferences){
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ScrollView startTiming(Timing[] arrayListTiming) {  //главный экран
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
            button9.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {   //добавить таймер
                    try {
                        if (altNumber < arrayListTiming.length) {
                            linearLayout.addView(arrayListTiming[altNumber].newTiming());
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
                        linearLayout.removeView(arrayListTiming[altNumber].removeLinerLayout1());
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

            myTargetView = new MyTargetView(context);
            myTargetView.setSlotId(1563231);
            LinearLayout.LayoutParams myTargetViewParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            myTargetView.setLayoutParams(myTargetViewParams);
            myTargetView.setListener(new MyTargetView.MyTargetViewListener() {
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
            myTargetView.load();


            for (int i = 0; i < altNumber; i++) {
                linearLayout.addView(arrayListTiming[i].newTiming());
            }
            return scrollView;

        } catch (Exception exception) {
            Toast.makeText(context, exception.toString(),
                    Toast.LENGTH_SHORT).show();
            Log.e("ERROR", "Exception", exception);
            return null;
        }
    }
}
