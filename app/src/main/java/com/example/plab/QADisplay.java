package com.example.plab;
import androidx.annotation.Nullable;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.util.ArrayList;

public class QADisplay extends AppCompatActivity {

    RecyclerView qarecyclerview;
    FloatingActionButton addquestions;


    MyDatabaseHelperQA myDB;
    ArrayList<String> qa_id, qa_question, qa_answer;
    CustomAdapterQA customAdapterQA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qadisplay);

        qarecyclerview = findViewById(R.id.qarecyclerview);
        addquestions = findViewById(R.id.addquestions);

        addquestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(QADisplay.this,AddqaActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelperQA(QADisplay.this);
        qa_id = new ArrayList<>();
        qa_question = new ArrayList<>();
        qa_answer = new ArrayList<>();

        storeDataInArrays();

        customAdapterQA = new CustomAdapterQA(QADisplay.this,this, qa_id,qa_question, qa_answer);
        qarecyclerview.setAdapter(customAdapterQA);
        qarecyclerview.setLayoutManager(new LinearLayoutManager(QADisplay.this));
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
            while (cursor.moveToNext()){
                qa_id.add(cursor.getString(0));
                qa_answer.add(cursor.getString(1));
                qa_question.add(cursor.getString(2));
            }


        }
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
            Intent intent= new Intent(QADisplay.this, QADisplay.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}