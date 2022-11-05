package com.example.plab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList prac_id, prac_title, prac_lesson, prac_steps;

    CustomAdapter(Context context,
                  ArrayList prac_id,
                  ArrayList prac_title,
                  ArrayList prac_lesson,
                  ArrayList prac_steps){
        this.context = context;
        this.prac_id = prac_id;
        this.prac_title = prac_title;
        this.prac_lesson = prac_lesson;
        this.prac_steps = prac_steps;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.prac_id_txt.setText(String.valueOf(prac_id.get(position)));
        holder.prac_title_txt.setText(String.valueOf(prac_title.get(position)));
        holder.prac_lesson_txt.setText(String.valueOf(prac_lesson.get(position)));
        holder.prac_steps_txt.setText(String.valueOf(prac_steps.get(position)));
    }

    @Override
    public int getItemCount() {
        return prac_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView prac_id_txt, prac_title_txt, prac_lesson_txt, prac_steps_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prac_id_txt = itemView.findViewById(R.id.prac_id_txt);
            prac_title_txt = itemView.findViewById(R.id.prac_title_txt);
            prac_lesson_txt = itemView.findViewById(R.id.prac_lesson_txt);
            prac_steps_txt = itemView.findViewById(R.id.prac_steps_txt);
        }
    }
}
