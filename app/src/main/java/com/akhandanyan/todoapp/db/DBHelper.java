package com.akhandanyan.todoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.akhandanyan.todoapp.db.TodoItemContract.*;
import static com.akhandanyan.todoapp.db.TodoItemContract.TodoEntry.*;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "todo";
    private static final int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODO_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TODO_ITEMS);
        db.execSQL(SQL_CREATE_TODO_ITEMS);

    }

    private static final String SQL_CREATE_TODO_ITEMS =
            "CREATE TABLE IF NOT EXISTS " + TodoEntry.TABLE_NAME + "( " +
                    TodoEntry._ID + " INTEGER PRIMARY KEY, " +
                    TodoEntry.TITLE + "TEXT NOT NULL, " +
                    TodoEntry.DESCRIPTION + "TEXT NOT NULL, " +
                    TodoEntry.DATE + "DATE, " +
                    TodoEntry.ISREMIND + "BOOLEAN, " +
                    TodoEntry.REPEAT_TYPE + "TEXT, " +
                    TodoEntry.PRIORITY + "INTEGER )";


    private final static String SQL_DELETE_TODO_ITEMS =
            "DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME;
}