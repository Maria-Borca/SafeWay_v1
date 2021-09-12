package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.safeway.model.Translation;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    CheckBox checkBox1, checkBox2;
    TextView title;
    boolean checked1;
    boolean checked2;

    private Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        translation = Translation.getInstance();

        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        title = findViewById(R.id.title_text);

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked1) {
                    checkBox2.setChecked(true);
                    checkBox1.setChecked(false);
                    checked1 = false;
                    checked2= true;
                } else {
                    checkBox2.setChecked(false);
                    checkBox1.setChecked(true);
                    checked1 = true;
                    checked2 = false;
                }
                translation.setLanguage();
                checkTranslations();
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checked2) {
                    checkBox2.setChecked(true);
                    checkBox1.setChecked(false);
                    checked2 = true;
                    checked1 = false;
                } else {
                    checkBox2.setChecked(false);
                    checkBox1.setChecked(true);
                    checked2 = false;
                    checked1 = true;
                }
                translation.setLanguage();
                checkTranslations();
            }
        });
    }

    public void openSettings(View view) {
        Intent intent = new Intent(LanguageActivity.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkTranslations() {
        if (translation.getLanguage() == "EN") {
            title.setText(R.string.language);
        } else {
            title.setText(R.string.language_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (translation.getLanguage() == "EN") {
            checkBox2.setChecked(false);
            checkBox1.setChecked(true);
            checked2 = false;
            checked1 = true;
        } else {
            checkBox2.setChecked(true);
            checkBox1.setChecked(false);
            checked2 = true;
            checked1 = false;
        }
        checkTranslations();
    }
}