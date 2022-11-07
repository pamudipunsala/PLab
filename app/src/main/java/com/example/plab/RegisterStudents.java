package com.example.plab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterStudents extends AppCompatActivity {

    TextInputEditText name, email, userName, pwd;
    Spinner type;

    Button buttonSignUp;
    MyDatabaseHelperReg DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        name = (TextInputEditText) findViewById(R.id.name);
        email = (TextInputEditText) findViewById(R.id.email);
        type = (Spinner) findViewById(R.id.type);
        userName = (TextInputEditText) findViewById(R.id.userName);
        pwd = (TextInputEditText) findViewById(R.id.pwd);

        buttonSignUp = (Button) findViewById(R.id.btnlogin);

        DB = new MyDatabaseHelperReg(this);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uName = userName.getText().toString();
                String password = pwd.getText().toString();

                if (uName.equals("")||password.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else {
                    Boolean checkuserpwd= DB.checkuNamePwd(uName, password);
                    if (checkuNamePwd==true){
                        Toast.makeText(Login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

    }
}


