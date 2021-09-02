package com.example.safeway.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safeway.AboutActivity;
import com.example.safeway.PrimaryActivity;
import com.example.safeway.R;
import com.example.safeway.SettingsActivity;
import com.example.safeway.ui.login.DBHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    DBHelper DB;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
        btnlogin = (Button) findViewById(R.id.login_btn);
        forgot=(TextView) findViewById(R.id.forgot_password);
        DB= new DBHelper(this);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields!", Toast.LENGTH_SHORT ).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Log in successful!", Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent(getApplicationContext(), PrimaryActivity.class);
                        startActivity((intent));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT ).show();
                    }
                }


            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);

            }
        });
    }

    public void openPrimary(View view) {
        Intent intent = new Intent(LoginActivity.this, PrimaryActivity.class);
        startActivity(intent);
    }

    public void openSignUp(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}