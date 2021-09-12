package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.safeway.model.Translation;

public class AboutActivity extends AppCompatActivity {

    TextView title, paragraph1, paragraph2;

    Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        title = findViewById(R.id.title_text);
        paragraph1 = findViewById(R.id.paragraph_1);
        paragraph2 = findViewById(R.id.paragraph_2);

        translation = Translation.getInstance();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(AboutActivity.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            title.setText(R.string.about);
            paragraph1.setText(R.string.about_paragraph_1);
            paragraph2.setText(R.string.about_paragraph_2);
        } else {
            title.setText(R.string.about_ro);
            paragraph1.setText(R.string.about_paragraph_1_ro);
            paragraph2.setText(R.string.about_paragraph_2_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}