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
import android.widget.Toast;

public class UpdateQA extends AppCompatActivity {
    EditText etaddquestion2, etaddanswer;
    Button qupdatebtn, qdeletebtn;

    String id, question, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_qa);

        etaddquestion2 = findViewById(R.id.etaddquestion2);
        etaddanswer = findViewById(R.id.etaddanswer);
        qupdatebtn = findViewById(R.id.qupdatebtn);
        qdeletebtn = findViewById(R.id.qdeletebtn);
        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(id);
        }

        qupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelperQA myDB = new MyDatabaseHelperQA(UpdateQA.this);
                question = etaddquestion2.getText().toString().trim();
                answer = etaddanswer.getText().toString().trim();
                myDB.updateQA(id, question, answer);

                Intent intent= new Intent(UpdateQA.this, QADisplay.class);
                startActivity(intent);
            }
        });

        qdeletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("question")
                && getIntent().hasExtra("answer")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            question = getIntent().getStringExtra("question");
            answer = getIntent().getStringExtra("answer");



            //Setting Intent Data
            etaddquestion2.setText(question);
            etaddanswer.setText(answer);


        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + question + " ?");
        builder.setMessage("Are you sure you want to delete " + question + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelperQA myDB = new MyDatabaseHelperQA(UpdateQA.this);
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
        if(item.getItemId() == R.id.menu){
            Intent intent= new Intent(UpdateQA.this, QADisplay.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



}