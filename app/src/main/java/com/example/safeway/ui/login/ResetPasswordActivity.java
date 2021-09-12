package com.example.safeway.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.safeway.R;
import com.example.safeway.model.Translation;
import com.example.safeway.ui.login.DBHelper;

public class ResetPasswordActivity extends AppCompatActivity {

    TextView username, title;
    EditText pass, repass;
    Button confirm;
    DBHelper DB;

    Translation translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        title = (TextView) findViewById(R.id.login_title);
        username = (TextView) findViewById(R.id.username_reset_text);
        pass = (EditText) findViewById(R.id.password_reset);
        repass = (EditText) findViewById(R.id.repassword_reset);
        confirm = (Button) findViewById(R.id.btnconfirm);

        translation = Translation.getInstance();

        DB = new DBHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if (password.equals(repassword)) {

                    Boolean checkpasswordupdate = DB.updatepassword(user, password);
                    if (checkpasswordupdate == true) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetPasswordActivity.this, "Password Update Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Password Not Update", Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(ResetPasswordActivity.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void checkTranslation() {
        if (translation.getLanguage() == "EN") {
            title.setText(R.string.reset_password);
            confirm.setText(R.string.reset_password);
            pass.setText(R.string.new_password, TextView.BufferType.EDITABLE);
            repass.setText(R.string.prompt_confirm_password, TextView.BufferType.EDITABLE);
        } else {
            title.setText(R.string.reset_password_ro);
            confirm.setText(R.string.reset_password_ro);
            pass.setText(R.string.new_password_ro, TextView.BufferType.EDITABLE);
            repass.setText(R.string.prompt_confirm_password_ro, TextView.BufferType.EDITABLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkTranslation();
    }
}