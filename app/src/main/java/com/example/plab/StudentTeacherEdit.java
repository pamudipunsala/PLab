package com.example.plab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentTeacherEdit extends AppCompatActivity {
    TextInputEditText name, email, userName, pwd;
    Spinner type;

    Button update_button, delete_button;

    String id, busid, stime, etime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        busid_input = findViewById(R.id.busid_input2);
        stime_input = findViewById(R.id.stime_input2);
        etime_input = findViewById(R.id.etime_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(busid);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelperReg myDB = new MyDatabaseHelperReg(UpdateActivity.this);
                busid = busid_input.getText().toString().trim();
                stime = stime_input.getText().toString().trim();
                etime = etime_input.getText().toString().trim();
                myDB.updateData(id, busid, stime, etime);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("busid") && getIntent().hasExtra("stime") &&
                getIntent().hasExtra("etime")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            busid = getIntent().getStringExtra("busid");
            stime = getIntent().getStringExtra("stime");
            etime = getIntent().getStringExtra("etime");


            //Setting Intent Data
            busid_input.setText(busid);
            stime_input.setText(stime);
            etime_input.setText(etime);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + busid + " ?");
        builder.setMessage("Are you sure you want to delete " + busid + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menubook){
            Intent intent= new Intent(UpdateActivity.this, Bookings.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menutimetable){
            Intent intent= new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(intent);

        }else if(item.getItemId() == R.id.menureview){
            Intent intent= new Intent(UpdateActivity.this, ReviewList.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
