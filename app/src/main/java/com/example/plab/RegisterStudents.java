package com.example.plab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterStudents extends AppCompatActivity {

    EditText name, email, userName, pwd;
    Spinner type;

    Button btnSign;
    MyDatabaseHelperReg DB;

    String ans = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        name = (EditText) findViewById(R.id.rname);
        email = (EditText) findViewById(R.id.rEmail);
        type = (Spinner) findViewById(R.id.rutype);
        userName = (EditText) findViewById(R.id.runame);
        pwd = (EditText) findViewById(R.id.rpwd);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelperReg myDB = new MyDatabaseHelperReg(RegisterStudents.this);

                Intent intent= new Intent(RegisterStudents.this, DisplayStudents.class);
                startActivity(intent);
            }
        });

    }
}

