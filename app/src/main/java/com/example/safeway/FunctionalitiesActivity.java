package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safeway.model.MaxLegalSpeed;
import com.example.safeway.model.RestRecommendation;
import com.example.safeway.model.Runnables.MaxLegalSpeedRunnable;
import com.example.safeway.model.Runnables.RestRecommendationRunnable;

public class FunctionalitiesActivity extends AppCompatActivity {

    private RestRecommendation restRecommendation;

    private static final String DEBUG_TAG = "DEBG";

    TextView display_state, display_countdown;
    TextView display_prompt_text;

    ImageView img_prompt;


    private MaxLegalSpeed maxLegalSpeed;

    TextView txt_speed;
    TextView txt_street_name;
    TextView txt_street_limit;
    TextView txt_city_name;

    static boolean check_first_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functionalities);

        Button button = (Button) findViewById(R.id.button_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_back();
            }
        });

        restRecommendation = RestRecommendation.getInstance();
        maxLegalSpeed = MaxLegalSpeed.getInstance();

        if (!check_first_time) { // Runs only once per session
            check_first_time = true;

            display_state = (TextView) findViewById(R.id.txt_state);
            display_countdown = (TextView) findViewById(R.id.txt_countdown);

            display_prompt_text = (TextView) findViewById(R.id.txt_prompt);
            display_prompt_text.setEnabled(false);
            display_prompt_text.setClickable(false);

            img_prompt = (ImageView) findViewById(R.id.image_prompt);
            img_prompt.setEnabled(false);
            img_prompt.setClickable(false);

            txt_speed = (TextView) findViewById(R.id.txt_current_speed);
            txt_street_name = (TextView) findViewById(R.id.txt_street_name);
            txt_street_limit = (TextView) findViewById(R.id.txt_street_limit);
            txt_city_name = (TextView) findViewById(R.id.txt_city);

            RestRecommendationRunnable rest_recommendation = new RestRecommendationRunnable( display_state, img_prompt, display_prompt_text, display_countdown, restRecommendation);
            MaxLegalSpeedRunnable max_legal_speed = new MaxLegalSpeedRunnable(txt_speed, txt_street_name, txt_street_limit, txt_city_name, this, maxLegalSpeed);

            Thread restRecommendationThread = new Thread(rest_recommendation);
            Thread maxLegalSpeedThread = new Thread(max_legal_speed);

            restRecommendationThread.start();
            maxLegalSpeedThread.start();
        }
    }

    public void go_back () {
       Intent intent = new Intent(FunctionalitiesActivity.this, PrimaryActivity.class);
       startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        go_back();
    }
}