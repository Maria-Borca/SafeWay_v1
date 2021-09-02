package com.example.safeway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_profile);

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
}