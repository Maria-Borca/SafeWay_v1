package com.example.safeway.model.Runnables;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.example.safeway.model.MaxLegalSpeed;

public class MaxLegalSpeedRunnable implements  Runnable {
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    TextView txt_speed, txt_street_name, txt_street_limit, txt_city_name;

    MaxLegalSpeed max_legal_speed;

    public MaxLegalSpeedRunnable (TextView txt_speed2, TextView txt_street_name2, TextView txt_street_limit2, TextView txt_city_name2, Context context2, MaxLegalSpeed max_legal_speed2) {
        this.txt_speed = txt_speed2;
        this.txt_street_name = txt_street_name2;
        this.txt_street_limit = txt_street_limit2;
        this.txt_city_name = txt_city_name2;
        this.mContext = context2;
        this.max_legal_speed = max_legal_speed2;
    }

    @Override
    public void run(){
        max_legal_speed.initialization(txt_speed, txt_street_name, txt_street_limit, txt_city_name, mContext);
    }
}
