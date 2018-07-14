package com.akhandanyan.todoapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.akhandanyan.todoapp.model.TodoItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.akhandanyan.todoapp.db.TodoItemContract.*;


public class DBManager {

    private DBHelper mDBHelper;

    public DBManager(Context context) {
        mDBHelper = new DBHelper(context);
    }

    public DBManager() {

    }

    public void insertTodoItem(TodoItem todoItem) {
        ContentValues contentValues = getContentValues(todoItem);
        mDBHelper.getWritableDatabase().insert(TodoEntry.TABLE_NAME,
                null, contentValues);
    }

    public void updateTodoItem(TodoItem todoItem) {
        String selected = TodoEntry._ID + "=?";
        String[] selectionArgs = {selected};
        ContentValues values = getContentValues(todoItem);
        mDBHelper.getWritableDatabase().update(TodoEntry.TABLE_NAME,
                values, selected, selectionArgs);
    }


    public void removeTodoItem(String id) {
        id=TodoEntry._ID;
        String selected = TodoEntry._ID + "=?";
        String[] selectionArgs = {selected};
        mDBHelper.getWritableDatabase().delete(TodoEntry.TABLE_NAME, selected, selectionArgs);

    }

    private TodoCursorWrapper query(String whereClause, String[] whereArgs) {

        Cursor cursor = mDBHelper.getWritableDatabase().query(TodoEntry.TABLE_NAME, null, whereClause,
                whereArgs, null, null, null);
        return new TodoCursorWrapper(cursor);
    }

    public List<TodoItem> getTodoItems() {
        List<TodoItem> todoItems = new ArrayList<>();
        TodoCursorWrapper cursor = (TodoCursorWrapper) mDBHelper.getReadableDatabase().query(TodoEntry.TABLE_NAME, null, null,
                null, null, null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                todoItems.add(cursor.getTodoItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return todoItems;
    }

    private static ContentValues getContentValues(TodoItem todoItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoItemContract.TodoEntry.TITLE, "First todo item");
        contentValues.put(TodoEntry.DATE, todoItem.getDate().getTime());
        contentValues.put(TodoEntry.ISREMIND, todoItem.isShouldRemind());
        contentValues.put(TodoEntry.REPEAT_TYPE, String.valueOf(todoItem.getRepeatType()));
        contentValues.put(TodoEntry.PRIORITY, todoItem.getPriority());
        return contentValues;
    }


}
