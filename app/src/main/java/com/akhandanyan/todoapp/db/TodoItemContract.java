package com.akhandanyan.todoapp.db;

import android.provider.BaseColumns;

public class TodoItemContract implements BaseColumns {

    public TodoItemContract() {
    }

    public static class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo_items";
        public static final String TITLE = "Title";
        public static final String DESCRIPTION = "description";
        public static final String DATE = "date";
        public static final String ISREMIND = "isRemind";
        public static final String REPEAT_TYPE = "repeat_type";
        public static final String PRIORITY = "priority";
    }

}
