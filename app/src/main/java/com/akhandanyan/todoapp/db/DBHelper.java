package com.akhandanyan.todoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.akhandanyan.todoapp.db.TodoItemContract.TodoEntry.*;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "todo.db";
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

    private final static String SQL_CREATE_TODO_ITEMS = "CREATE TABLE " +
            TABLE_NAME + "(" +
            _ID + "INTEGER PRIMARY KEY, " +
            TITLE + "TEXT NOT NULL, " +
            DESCRIPTION + "TEXT NOT NULL, " +
            DATE + "DATE, " +
            ISREMIND + "BOOLEAN " +
            REPEAT_TYPE + "TEXT, " +
            PRIORITY + "INTEGER )";


    private final static String SQL_DELETE_TODO_ITEMS =
            "DROP TABLE IF EXISTS " + TodoItemContract.TodoEntry.TABLE_NAME;
}