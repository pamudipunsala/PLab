package com.example.plab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PhyPractical.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "phy_practical";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "prac_title";
    private static final String COLUMN_LESSON = "prac_lesson";
    private static final String COLUMN_STEPS = "prac_steps";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_LESSON + " TEXT, " +
                        COLUMN_STEPS + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
