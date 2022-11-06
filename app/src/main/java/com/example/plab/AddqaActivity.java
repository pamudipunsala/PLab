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

    public class AddqaActivity extends AppCompatActivity {

        EditText etaddquestion;
        Button qaddbtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_addqa);

            etaddquestion= findViewById(R.id.etaddquestion);
            qaddbtn= findViewById(R.id.qaddbtn);
            qaddbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyDatabaseHelperQA myDB = new MyDatabaseHelperQA(AddqaActivity.this);
                    myDB.addQuestion(etaddquestion.getText().toString().trim());


                    Intent intent= new Intent(AddqaActivity.this, QADisplay.class);
                    startActivity(intent);
                }
            });

        }/*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.my_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.menubook){
                Intent intent= new Intent(AddBooking.this, Bookings.class);
                startActivity(intent);
            }else if(item.getItemId() == R.id.menutimetable){
                Intent intent= new Intent(AddBooking.this, MainActivity.class);
                startActivity(intent);

            }else if(item.getItemId() == R.id.menureview){
                Intent intent= new Intent(AddBooking.this, ReviewList.class);
                startActivity(intent);
            }
            return super.onOptionsItemSelected(item);
        }*/
    }