package com.gdeer.projectest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gdeer.projectest.model.Announcement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gdeer on 2015/10/15.
 */
public class ProjectTestDB {

    //数据库名
    public static final String DB_NAME = "project_test";
    //数据库版本
    public static final int VERSION = 1;
    private static ProjectTestDB projectTestDB;
    private SQLiteDatabase db;

    // 将构造方法私有化
    private ProjectTestDB(Context context) {
        DatabaseOpenHelper dbHelper = new DatabaseOpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    //获取ProjectTestDB的实例
    public synchronized static ProjectTestDB getInstance(Context context) {
        if (projectTestDB == null) {
            projectTestDB = new ProjectTestDB(context);
        }
        return projectTestDB;
    }

    //将Announcement实例存储到数据库
    public void saveAnnouncement(Announcement announcement) {
        if (announcement != null) {
            ContentValues values = new ContentValues();
            values.put("announcement_content", announcement.getAnnouncementContent());
            db.insert("Announcement", null, values);
        }
    }

    //从数据库读取公告信息
    public List<Announcement> loadAnnouncement() {
        List<Announcement> list = new ArrayList<>();
        Cursor cursor = db
                .query("Announcement", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Announcement announcement = new Announcement();
                announcement.setId(cursor.getInt(cursor.getColumnIndex("id")));
                announcement.setAnnouncementContent(cursor.getString(cursor
                        .getColumnIndex("announcement_content")));
                list.add(announcement);
            } while (cursor.moveToNext());
        }
        return list;
    }
}