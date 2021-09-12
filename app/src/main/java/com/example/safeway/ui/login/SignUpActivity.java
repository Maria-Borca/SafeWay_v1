package com.example.safeway.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.safeway.PrimaryActivity;
import com.example.safeway.R;

public class SignUpActivity extends AppCompatActivity {

    EditText username, email, password, confirm_password;
    TextView login, title;
    Button signup;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username= findViewById(R.id.username);
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        confirm_password= findViewById(R.id.confirm_password);
        signup= findViewById(R.id.btnsignup);
        login= findViewById(R.id.old_account);

        DB= new DBHelper(this);


        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = confirm_password.getText().toString();

                if(user.equals("") || mail.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(SignUpActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        boolean checkuser = DB.checkusername(user);
                        if(!checkuser){
                            Boolean insert = DB.insertdata(user, mail, pass);
                            if(insert){
                                Toast.makeText(SignUpActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), PrimaryActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                            }

                        }
                        else{
                            Toast.makeText(SignUpActivity.this,"User already exists! Please log in.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public void openPrimary(View view) {
        Intent intent = new Intent(SignUpActivity.this, PrimaryActivity.class);
        startActivity(intent);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}