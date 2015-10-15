package com.gdeer.projectest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gdeer on 2015/10/15.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Announcement";
    public static final String CREATE_ANNOUNCEMENT = "create table "+ TABLE_NAME +" ("
            + "id integer primary key autoincrement, "
            + "announcement_content text)";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ANNOUNCEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
