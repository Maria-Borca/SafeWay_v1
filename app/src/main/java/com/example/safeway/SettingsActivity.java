package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.safeway.model.RestRecommendation;
import com.example.safeway.model.Translation;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    private TextView settings_title, settings_about, settings_language;
    String title_string, about_string, language_string;

    private Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_title = findViewById(R.id.settings_title_text);
        settings_about = findViewById(R.id.about_text);
        settings_language = findViewById(R.id.language_text);

        translation = Translation.getInstance();
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

    public void setTV_Title(String new_value) {
        Log.d("DEBG", "SETTINGS FUNCTION: " + String.valueOf(settings_title));
        settings_title.setText(new_value);
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            settings_title.setText(R.string.settings);
            settings_about.setText(R.string.about);
            settings_language.setText(R.string.language);
        } else {
            settings_title.setText(R.string.settings_ro);
            settings_about.setText(R.string.about_ro);
            settings_language.setText(R.string.language_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}