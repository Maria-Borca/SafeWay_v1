package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.safeway.model.Translation;

public class ForBeginnersTipsActivity extends AppCompatActivity {

    private TextView title, tip1, tip2, tip3, tip4, tip5, tip6, tip7, tip8;
    private Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_beginners_tips);

        title = findViewById(R.id.title);
        tip1 = findViewById(R.id.tip1);
        tip2 = findViewById(R.id.tip2);
        tip3 = findViewById(R.id.tip3);
        tip4 = findViewById(R.id.tip4);
        tip5 = findViewById(R.id.tip5);
        tip6 = findViewById(R.id.tip6);
        tip7 = findViewById(R.id.tip7);
        tip8 = findViewById(R.id.tip8);

        translation = Translation.getInstance();
    }

    public void openTipsAndTricks(View view) {
        Intent intent = new Intent(ForBeginnersTipsActivity.this, TipsTricksActivity.class);
        startActivity(intent);
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            title.setText(R.string.tips_tricks_for_beginners);
            tip1.setText(R.string.tip35);
            tip2.setText(R.string.tip36);
            tip3.setText(R.string.tip37);
            tip4.setText(R.string.tip38);
            tip5.setText(R.string.tip39);
            tip6.setText(R.string.tip40);
            tip7.setText(R.string.tip41);
            tip8.setText(R.string.tip42);
        } else {
            title.setText(R.string.tips_tricks_for_beginners_ro);
            tip1.setText(R.string.tip35_ro);
            tip2.setText(R.string.tip36_ro);
            tip3.setText(R.string.tip37_ro);
            tip4.setText(R.string.tip38_ro);
            tip5.setText(R.string.tip39_ro);
            tip6.setText(R.string.tip40_ro);
            tip7.setText(R.string.tip41_ro);
            tip8.setText(R.string.tip42_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}