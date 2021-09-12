package com.example.safeway;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safeway.model.Translation;
import com.example.safeway.ui.login.LoginActivity;
import com.example.safeway.ui.login.SignUpActivity;

import org.jetbrains.annotations.NotNull;

import java.security.acl.Permission;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    public static int REQUEST_PERMISSIONS = 1;

    Button start_btn;

    Translation translation;
    TextView team_text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        team_text = findViewById(R.id.team_text);
        button = findViewById(R.id.get_started_btn);

        translation = Translation.getInstance();

        start_btn = (Button) findViewById(R.id.get_started_btn);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            start_btn.setEnabled(false);
        }

        request_permissions2();
    }
    public void openSignUp(View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void request_permissions() {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is needed to access your location...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("DEBG", String.valueOf(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)));
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, REQUEST_PERMISSIONS);
                        }
                    })
                    .create().show();
    }

    public void request_permissions2 () {
        Log.d("DEBG", "requested");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {

            if(!(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.INTERNET)))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, REQUEST_PERMISSIONS);
                Log.d("DEBG", String.valueOf(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)));
            }
        }
        else {
            request_permissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSIONS) {
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED || grantResults[2] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "You Will have to manually set this permission", Toast.LENGTH_SHORT).show();
                //request_permissions2();
            }
            else {
                start_btn.setEnabled(true);
            }
        }
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            button.setText(R.string.start_driving_safer);
            team_text.setText(R.string.team_dev);
        } else {
            button.setText(R.string.start_driving_safer_ro);
            team_text.setText(R.string.team_dev_ro);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}