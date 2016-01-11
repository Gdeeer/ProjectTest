package com.gdeer.projectest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gdeer on 2015/10/15.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Announcement";
    private static final String TABLE_NAME2 = "MyFollow";
    public static final String CREATE_ANNOUNCEMENT = "create table "+ TABLE_NAME +" ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "tag text, "
            + "count text, "
            + "describe text)";

    public static final String CREATE_MYFOLLOW = "create table "+ TABLE_NAME2 +" ("
            + "id integer primary key autoincrement, "
            + "title text,"
            + "type integer)";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ANNOUNCEMENT);
        db.execSQL(CREATE_MYFOLLOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
