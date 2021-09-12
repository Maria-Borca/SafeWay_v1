package com.example.safeway;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.safeway.model.RestRecommendation;
import com.example.safeway.model.Runnables.RestRecommendationRunnable;
import com.example.safeway.model.Translation;

import org.w3c.dom.Text;

public class PrimaryActivity extends AppCompatActivity {

    Translation translation;
    TextView drive_safely, motto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        drive_safely = findViewById(R.id.title_text);
        motto = findViewById(R.id.motto);

        translation = Translation.getInstance();


        TextView viewFunctionalities = (TextView) findViewById(R.id.txt_functionalities_activity);

        viewFunctionalities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFunctionalities();
            }
        });


    }

    public void openFunctionalities () {
        Intent intent = new Intent(PrimaryActivity.this, FunctionalitiesActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(PrimaryActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openProfile(View view) {
        Intent intent = new Intent(PrimaryActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            drive_safely.setText(R.string.drive_safely);
            motto.setText(R.string.we_ve_got_you_covered);
        } else {
            drive_safely.setText(R.string.drive_safely_ro);
            motto.setText(R.string.we_ve_got_you_covered_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}