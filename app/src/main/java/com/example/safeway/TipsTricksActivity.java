package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.safeway.model.Translation;

public class TipsTricksActivity extends AppCompatActivity {

    private TextView important, good_to_know, for_beginners;
    private Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_tricks);

        important = findViewById(R.id.important);
        good_to_know = findViewById(R.id.good_to_know);
        for_beginners = findViewById(R.id.for_beginners);

        translation = Translation.getInstance();
    }

    public void openProfile(View view) {
        Intent intent = new Intent(TipsTricksActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openImportant(View view) {
        Intent intent = new Intent(TipsTricksActivity.this, ImportantTipsActivity.class);
        startActivity(intent);
    }

    public void openGoodToKnow(View view) {
        Intent intent = new Intent(TipsTricksActivity.this, GoodToKnowTipsActivity.class);
        startActivity(intent);
    }

    public void openForBeginners(View view) {
        Intent intent = new Intent(TipsTricksActivity.this, ForBeginnersTipsActivity.class);
        startActivity(intent);
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            important.setText(R.string.important);
            good_to_know.setText(R.string.good_to_know);
            for_beginners.setText(R.string.for_beginners);
        } else {
            important.setText(R.string.important_ro);
            good_to_know.setText(R.string.good_to_know_ro);
            for_beginners.setText(R.string.for_beginners_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}