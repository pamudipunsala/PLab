package com.example.plab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    private EditText uName, pwd;
    Button buttonLogin;
    MyDatabaseHelperReg DB;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uName = (EditText) findViewById(R.id.luname);
        pwd = (EditText) findViewById(R.id.lpwd);

        DB = new MyDatabaseHelperReg(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = uName.getText().toString();
                String pass = pwd.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else {
                    Boolean checkuserpass= DB.checkuserNamepassword(user, pass);
                    if (checkuserpass==true){
                        Toast.makeText(Login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DisplayStudents.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

    }
}


