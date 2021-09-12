package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.safeway.model.Translation;

public class GoodToKnowTipsActivity extends AppCompatActivity {

    private TextView title, tip1, tip2, tip3, tip4, tip5, tip6, tip7, tip8, tip9, tip10, tip11, tip12, tip13, tip14, tip15, tip16, tip17, tip18, tip19, tip20, tip21, tip22, tip23;
    private Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_to_know_tips);

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
        tip12 = findViewById(R.id.tip12);
        tip13 = findViewById(R.id.tip13);
        tip14 = findViewById(R.id.tip14);
        tip15 = findViewById(R.id.tip15);
        tip16 = findViewById(R.id.tip16);
        tip17 = findViewById(R.id.tip17);
        tip18 = findViewById(R.id.tip18);
        tip19 = findViewById(R.id.tip19);
        tip20 = findViewById(R.id.tip20);
        tip21 = findViewById(R.id.tip21);
        tip22 = findViewById(R.id.tip22);
        tip23 = findViewById(R.id.tip23);

        translation = Translation.getInstance();

    }

    public void openTipsAndTricks(View view) {
        Intent intent = new Intent(GoodToKnowTipsActivity.this, TipsTricksActivity.class);
        startActivity(intent);
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            title.setText(R.string.good_to_know_tips_tricks);
            tip1.setText(R.string.tip12);
            tip2.setText(R.string.tip13);
            tip3.setText(R.string.tip14);
            tip4.setText(R.string.tip15);
            tip5.setText(R.string.tip16);
            tip6.setText(R.string.tip17);
            tip7.setText(R.string.tip18);
            tip8.setText(R.string.tip19);
            tip9.setText(R.string.tip20);
            tip10.setText(R.string.tip21);
            tip11.setText(R.string.tip22);
            tip12.setText(R.string.tip23);
            tip13.setText(R.string.tip24);
            tip14.setText(R.string.tip25);
            tip15.setText(R.string.tip26);
            tip16.setText(R.string.tip27);
            tip17.setText(R.string.tip28);
            tip18.setText(R.string.tip29);
            tip19.setText(R.string.tip30);
            tip20.setText(R.string.tip31);
            tip21.setText(R.string.tip32);
            tip22.setText(R.string.tip33);
            tip23.setText(R.string.tip34);
        } else {
            title.setText(R.string.good_to_know_tips_tricks_ro);
            tip1.setText(R.string.tip12_ro);
            tip2.setText(R.string.tip13_ro);
            tip3.setText(R.string.tip14_ro);
            tip4.setText(R.string.tip15_ro);
            tip5.setText(R.string.tip16_ro);
            tip6.setText(R.string.tip17_ro);
            tip7.setText(R.string.tip18_ro);
            tip8.setText(R.string.tip19_ro);
            tip9.setText(R.string.tip20_ro);
            tip10.setText(R.string.tip21_ro);
            tip11.setText(R.string.tip22_ro);
            tip12.setText(R.string.tip23_ro);
            tip13.setText(R.string.tip24_ro);
            tip14.setText(R.string.tip25_ro);
            tip15.setText(R.string.tip26_ro);
            tip16.setText(R.string.tip27_ro);
            tip17.setText(R.string.tip28_ro);
            tip18.setText(R.string.tip29_ro);
            tip19.setText(R.string.tip30_ro);
            tip20.setText(R.string.tip31_ro);
            tip21.setText(R.string.tip32_ro);
            tip22.setText(R.string.tip33_ro);
            tip23.setText(R.string.tip34_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}