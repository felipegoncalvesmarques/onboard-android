package com.example.taqtile.onboard_android;

import android.provider.BaseColumns;

/**
 * Created by taqtile on 1/4/17.
 */

public final class UserContract {
    private UserContract() {};

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_AVATAR = "avatar";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Users.TABLE_NAME + " (" +
                    Users._ID + " INTEGER PRIMARY KEY," +
                    Users.COLUMN_NAME_FIRST_NAME + " TEXT," +
                    Users.COLUMN_NAME_LAST_NAME + " TEXT," +
                    Users.COLUMN_NAME_AVATAR + " TEXT);";
    public static final String SQL_DROP_ENTRIES =
            "DROP TABLE IF EXISTS " + Users.TABLE_NAME;
}
