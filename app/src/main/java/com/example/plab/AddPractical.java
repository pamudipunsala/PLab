package com.example.plab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddPractical extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> prac_id, prac_title, prac_lesson, prac_steps;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_practical);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPractical.this, AddPracticalActivity.class);
                startActivity(intent);

            }
        });

        myDB = new MyDatabaseHelper(AddPractical.this);
        prac_id = new ArrayList<>();
        prac_title = new ArrayList<>();
        prac_lesson = new ArrayList<>();
        prac_steps = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(AddPractical.this, this, prac_id, prac_title, prac_lesson, prac_steps);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddPractical.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                prac_id.add(cursor.getString(0));
                prac_title.add(cursor.getString(1));
                prac_lesson.add(cursor.getString(2));
                prac_steps.add(cursor.getString(3));
            }
        }
    }
}