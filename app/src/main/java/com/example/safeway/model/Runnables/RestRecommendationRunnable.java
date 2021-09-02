package com.example.safeway.model.Runnables;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.safeway.model.RestRecommendation;

public class RestRecommendationRunnable implements Runnable{

    final int DRIVING_TIME = 20;

    TextView display_state;
    TextView display_prompt_text;
    TextView display_countdown;
    ImageView img_prompt;

    RestRecommendation local_rest_recommendation;

    public RestRecommendationRunnable(TextView display_state2, ImageView img_prompt2, TextView display_prompt_text2, TextView display_countdown2, RestRecommendation local_rest_recommendation2){
        this.display_state = display_state2;
        this.img_prompt = img_prompt2;
        this.display_prompt_text = display_prompt_text2;
        this.display_countdown = display_countdown2;
        this.local_rest_recommendation = local_rest_recommendation2;
    }

    @Override
    public void run() {
        local_rest_recommendation.initialization("Drive", DRIVING_TIME * 1000, display_state, img_prompt, display_prompt_text, display_countdown);
    }
}
