package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.safeway.model.Translation;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.safeway.R.layout.*;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    private LineChart distanceChart;

    private Translation translation;

    TextView top_achievements;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_profile);

        top_achievements = findViewById(R.id.top_achievements);
        button = findViewById(R.id.button);

        translation = Translation.getInstance();

        distanceChart = (LineChart) findViewById(R.id.distance_chart);
        distanceChart.setDragEnabled(true);
        distanceChart.setScaleEnabled(true);

        ArrayList<com.github.mikephil.charting.data.Entry> yValues = new ArrayList<>();
        yValues.add(new com.github.mikephil.charting.data.Entry(0, 60f));
        yValues.add(new com.github.mikephil.charting.data.Entry(1, 30f));
        yValues.add(new com.github.mikephil.charting.data.Entry(2, 70f));
        yValues.add(new com.github.mikephil.charting.data.Entry(3, 20f));
        yValues.add(new com.github.mikephil.charting.data.Entry(4, 90f));
        yValues.add(new com.github.mikephil.charting.data.Entry(5, 150f));


        LineDataSet set1 = new LineDataSet(yValues, "Dataset 1");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        distanceChart.setData(data);;

    }

    public void openPrimary(View view) {
        Intent intent = new Intent(ProfileActivity.this, PrimaryActivity.class);
        startActivity(intent);
    }

    public void openAchievements(View view) {
        Intent intent = new Intent(ProfileActivity.this, AchievementsActivity.class);
        startActivity(intent);
    }

    public void openTipsAndTricks(View view) {
        Intent intent = new Intent(ProfileActivity.this, TipsTricksActivity.class);
        startActivity(intent);
    }

    public void checkTranslationProfile() {
        if (translation.getLanguage() == "EN") {
            top_achievements.setText(R.string.top_achievements);
            button.setText(R.string.achievements);
        } else {
            top_achievements.setText(R.string.top_achievements_ro);
            button.setText(R.string.achievements_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslationProfile();
    }
}