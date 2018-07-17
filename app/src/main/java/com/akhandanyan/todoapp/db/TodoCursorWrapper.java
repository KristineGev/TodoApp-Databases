package com.akhandanyan.todoapp.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.akhandanyan.todoapp.model.TodoItem;

import java.util.Date;

import static com.akhandanyan.todoapp.db.TodoItemContract.*;
import static java.lang.String.valueOf;

public class TodoCursorWrapper extends CursorWrapper {

    public TodoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TodoItem getTodoItem() {
        String idString = getString(getColumnIndex(TodoItemContract.TodoEntry._ID));
        String title = getString(getColumnIndex(TodoEntry.TITLE));
        String description = getString(getColumnIndex(TodoEntry.DESCRIPTION));
        long date = getLong(getColumnIndex(TodoEntry.DATE));
        int isRemind = getInt(getColumnIndex(TodoEntry.ISREMIND));
        String repeat_type = getString(getColumnIndex(TodoEntry.REPEAT_TYPE));
        int priority = getInt(getColumnIndex(TodoEntry.PRIORITY));

        TodoItem todoItem = new TodoItem(idString);
        todoItem.setTitle(title);
        todoItem.setDescription(description);
        todoItem.setDate(new Date(date));
        todoItem.setShouldRemind(isRemind != 0);
        todoItem.setRepeatType(repeat_type);
        todoItem.setPriority(priority);

        return todoItem;
    }


}

