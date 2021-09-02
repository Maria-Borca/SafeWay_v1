package com.example.safeway.model;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safeway.FunctionalitiesActivity;

public class RestRecommendation {

    private static final String DEBUG_TAG = "DEBG";

    final int DRIVING_TIME = 20;
    final int REST_TIME = 10;

    TextView display_state;
    TextView display_prompt_text;
    TextView display_countdown;
    ImageView img_prompt;

    /*
        Singleton Support (Thread Safe)
     */
    private static volatile RestRecommendation instance;
    private static Object mutex = new Object();

    private RestRecommendation() {
    }

    public static RestRecommendation getInstance() {
        RestRecommendation result = instance;
        if (instance == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new RestRecommendation();
            }
        }
        return instance;
    }
    /////////////////////////////////////////////////////////////

    public void initialization(String message, int interval, TextView display_state2, ImageView img_prompt2, TextView display_prompt_text2, TextView display_countdown2) {
        /*
            Initializes all the variables we need for displaying this functionality and starts the Drive & Rest cycle.

            `message` -> The message we want to display when running the timer (for Drive or Rest periods).
            `interval` -> Amount of time for our "Timer" to run (in this case it's 20 seconds).
            `display_state2` -> TextView responsible for the state of our Timer (Drive / Rest).
            `img_prompt2` -> A background for user to interact to in order for the next timer period to begin.
            `display_prompt_text2` -> TextView responsible for the text that it's displayed over the ImageView.
            `display_countdown2` -> Countdown for the Rest Time.

         */

        display_state = display_state2;
        img_prompt = img_prompt2;
        display_prompt_text = display_prompt_text2;
        display_countdown = display_countdown2;

        Log.d(DEBUG_TAG, "Started the process");

        start_timer(message, interval);

    }

    public void start_timer(String message, int interval) {
        /*
            Function responsible with running the timer (Drive or Rest).

            `message` -> The message we want to display when running the timer (for Drive or Rest periods).
            `interval` -> Amount of time for our "Timer" to run.
         */
        Log.d(DEBUG_TAG, "Started Timer");
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() { // This function runs once the handler call went through and the `interval` ended
                if (message.equals("Drive")) { // If the last `message` was "Drive", we call the function again, but for the "Rest" message with a different interval. And vice-versa
                    show_prompt(1); // Displays the prompt and asks the user to press on the screen in order for the rest timer to begin
                } else {
                    show_prompt(0);
                }
            }
        };
        display_state.setText(message);
        Log.d(DEBUG_TAG, message);
        handler.postDelayed(runnable, interval); // Fire up the timer
        if (message.equals("Rest")) { // If the user is in the Rest Period, we start a visual representation of the timer
            start_countdown_timer();
        }
    }

    private void show_prompt(int drive_or_rest) {
        /*
            Function responsible with showing the Prompt and waiting for user interaction.

            `drive_or_rest` -> Variable for keeping track of the prompts, and which one to display (for starting the Drive Timer, or the Rest one) || 0 -> Drive, 1 -> Rest.
         */
        rest_prompt_manager(drive_or_rest, true);
        img_prompt.setOnClickListener(new View.OnClickListener() { // Listens for any interaction, if it catches anything, it will start the next Time Period.
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "Clicked"); // DEBUGGING
                rest_prompt_manager(drive_or_rest, false); // Disable the prompt after user interacts with it.
                if (drive_or_rest == 0) {
                    start_timer("Drive", DRIVING_TIME * 1000);
                } else {
                    start_timer("Rest", REST_TIME * 1000);
                }
            }
        });
    }

    private void rest_prompt_manager(int drive_or_rest, boolean turn_on) {
        /*
            Function responsible with enabling/disabling the Prompt and the text over it.

            `drive_or_rest` -> Variable for keeping track of which message to be displayed.
            `turn_on` -> Enables (True)/Disables (False) the prompt.
         */
        if (drive_or_rest == 0) {
            display_prompt_text.setText("Press To Start The Drive Timer");
        } else {
            display_prompt_text.setText("Press To Start The Rest Timer");
        }

        if (turn_on) {
            img_prompt.setVisibility(View.VISIBLE);
            img_prompt.setEnabled(turn_on);
            img_prompt.setClickable(turn_on);

            display_prompt_text.setEnabled(turn_on);
            display_prompt_text.setVisibility(View.VISIBLE);
        } else {
            img_prompt.setVisibility(View.INVISIBLE);
            img_prompt.setEnabled(turn_on);
            img_prompt.setClickable(turn_on);

            display_prompt_text.setEnabled(turn_on);
            display_prompt_text.setVisibility(View.INVISIBLE);
        }
    }

    private void start_countdown_timer() {
        /*
            Function responsible with the visual representation of the Rest Period Timer.
         */
        final int[] counter = {10};
        new CountDownTimer(REST_TIME * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
               Handler handler = new Handler(Looper.getMainLooper());
               Runnable runnable = new Runnable(){
                    @Override
                    public void run() {
                        display_countdown.setText(String.valueOf(counter[0]));
                        Log.d(DEBUG_TAG, String.valueOf(display_countdown));
                        counter[0]--;
                    }
               };
               handler.post(runnable);
            }
            public void onFinish() {
                display_countdown.setText("Rest Timer Ended");
            }
        }.start();
    }

}
