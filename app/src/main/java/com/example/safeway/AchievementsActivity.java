package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AchievementsActivity extends AppCompatActivity {

    ImageView achievement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
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

}