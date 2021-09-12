package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.safeway.model.Translation;

public class AchievementsActivity extends AppCompatActivity {

    ImageView achievement;

    Translation translation;

    TextView title, td, dd, sl, br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        title = findViewById(R.id.achievement);
        td = findViewById(R.id.total_distance);
        dd = findViewById(R.id.daily_distance);
        sl = findViewById(R.id.speed_limit);
        br = findViewById(R.id.taking_breaks);

        translation = Translation.getInstance();
    }

    public void openProfile(View view) {
        Intent intent = new Intent(AchievementsActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void setUnlocked(int id) {
        achievement = (ImageView) findViewById(id);
        String backgroundImageName = (String) achievement.getTag();
        String unlocked = backgroundImageName.substring(0, backgroundImageName.length() - 7);
        Context context = achievement.getContext();
        int source = context.getResources().getIdentifier(unlocked, "drawable", context.getPackageName());
        achievement.setImageResource(source);
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            title.setText(R.string.achievements);
            td.setText(R.string.total_distance);
            dd.setText(R.string.daily_distance);
            sl.setText(R.string.speed_limit);
            br.setText(R.string.taking_breaks);
        } else {
            title.setText(R.string.achievements_ro);
            td.setText(R.string.total_distance_ro);
            dd.setText(R.string.daily_distance_ro);
            sl.setText(R.string.speed_limit_ro);
            br.setText(R.string.taking_breaks_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }

}