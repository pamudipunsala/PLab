package com.example.plab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterQA extends RecyclerView.Adapter<CustomAdapterQA.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList qa_id, qa_question, qa_answer;

    CustomAdapterQA(Activity activity, Context context, ArrayList qa_id, ArrayList qa_question, ArrayList qa_answer){
        this.activity = activity;
        this.context = context;
        this.qa_id = qa_id;
        this.qa_question = qa_question;
        this.qa_answer = qa_answer;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowqa, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.qa_id_txt.setText(String.valueOf(qa_id.get(position)));
        holder.question_txt.setText(String.valueOf(qa_question.get(position)));
        holder.answer_txt.setText(String.valueOf(qa_answer.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, UpdateQA.class);
                intent.putExtra("id", String.valueOf(qa_id.get(position)));
                intent.putExtra("question", String.valueOf(qa_question.get(position)));
                intent.putExtra("answer", String.valueOf(qa_answer.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return qa_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView qa_id_txt,question_txt, answer_txt;
        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            qa_id_txt = itemView.findViewById(R.id.qa_id_txt);
            question_txt = itemView.findViewById(R.id.question_txt);
            answer_txt = itemView.findViewById(R.id.answer_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
