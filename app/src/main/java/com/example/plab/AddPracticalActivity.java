package com.example.plab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPracticalActivity extends AppCompatActivity {

    EditText title_input, lesson_input, steps_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_practical2);

        title_input = findViewById(R.id.title_input);
        lesson_input = findViewById(R.id.lesson_input);
        steps_input = findViewById(R.id.steps_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddPracticalActivity.this);
                myDB.addPractical(title_input.getText().toString().trim(),
                        lesson_input.getText().toString().trim(),
                        steps_input.getText().toString().trim());
            }
        });
    }
}