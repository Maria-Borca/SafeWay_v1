package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.safeway.model.Translation;

public class ImportantTipsActivity extends AppCompatActivity {

    private TextView title, tip1, tip2, tip3, tip4, tip5, tip6, tip7, tip8, tip9, tip10, tip11;
    private Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_tips);

        title = findViewById(R.id.title);
        tip1 = findViewById(R.id.tip1);
        tip2 = findViewById(R.id.tip2);
        tip3 = findViewById(R.id.tip3);
        tip4 = findViewById(R.id.tip4);
        tip5 = findViewById(R.id.tip5);
        tip6 = findViewById(R.id.tip6);
        tip7 = findViewById(R.id.tip7);
        tip8 = findViewById(R.id.tip8);
        tip9 = findViewById(R.id.tip9);
        tip10 = findViewById(R.id.tip10);
        tip11 = findViewById(R.id.tip11);

        translation = Translation.getInstance();
    }

    public void openTipsAndTricks(View view) {
        Intent intent = new Intent(ImportantTipsActivity.this, TipsTricksActivity.class);
        startActivity(intent);
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            title.setText(R.string.important_tips_tricks);
            tip1.setText(R.string.tip1);
            tip2.setText(R.string.tip2);
            tip3.setText(R.string.tip3);
            tip4.setText(R.string.tip4);
            tip5.setText(R.string.tip5);
            tip6.setText(R.string.tip6);
            tip7.setText(R.string.tip7);
            tip8.setText(R.string.tip8);
            tip9.setText(R.string.tip9);
            tip10.setText(R.string.tip10);
            tip11.setText(R.string.tip11);
        } else {
            title.setText(R.string.important_tips_tricks_ro);
            tip1.setText(R.string.tip1_ro);
            tip2.setText(R.string.tip2_ro);
            tip3.setText(R.string.tip3_ro);
            tip4.setText(R.string.tip4_ro);
            tip5.setText(R.string.tip5_ro);
            tip6.setText(R.string.tip6_ro);
            tip7.setText(R.string.tip7_ro);
            tip8.setText(R.string.tip8_ro);
            tip9.setText(R.string.tip9_ro);
            tip10.setText(R.string.tip10_ro);
            tip11.setText(R.string.tip11_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }

}