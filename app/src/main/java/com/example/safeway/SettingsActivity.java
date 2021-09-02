package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void openPrimary(View view) {
        Intent intent = new Intent(SettingsActivity.this, PrimaryActivity.class);
        startActivity(intent);
    }

    public void openLanguage(View view) {
        Intent intent = new Intent(SettingsActivity.this, LanguageActivity.class);
        startActivity(intent);
    }

    public void openAbout(View view) {
        Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}