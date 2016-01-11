package com.gdeer.projectest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gdeer.projectest.model.Announcement;
import com.gdeer.projectest.model.ItemSquare;

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
            values.put("title", announcement.getTitle());
            values.put("tag", announcement.getTag());
            values.put("count", announcement.getcount());
            values.put("describe", announcement.getDescribe());
            db.insert("Announcement", null, values);
        }
    }

    //将关注栏目标题存储到数据库
    public void saveMyFollow(ItemSquare item) {
        if (item != null) {
            ContentValues values = new ContentValues();
            values.put("title", item.getItemName());
            values.put("type", item.getItemType());
            db.insert("MyFollow", null, values);
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
                announcement.setTitle(cursor.getString(cursor
                        .getColumnIndex("title")));
                announcement.setTag(cursor.getString(cursor
                        .getColumnIndex("tag")));
                announcement.setCount(cursor.getString(cursor
                        .getColumnIndex("count")));
                announcement.setDescribe(cursor.getString(cursor
                        .getColumnIndex("describe")));
                list.add(announcement);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //从数据库读取关注信息
    public List<ItemSquare> loadMyFollow() {
        List<ItemSquare> list = new ArrayList<>();
        Cursor cursor = db
                .query("MyFollow", null, null, null, null, null, null);
        //if (curso)
        if (cursor.moveToFirst()) {
            do {
                String title = "";
                title = cursor.getString(cursor.getColumnIndex("title"));
                Integer type = cursor.getInt(cursor.getColumnIndex("type"));
                ItemSquare item = new ItemSquare(title, type);
                list.add(item);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Announcement searchAndSet(String title) {
        Announcement announcement = new Announcement();
        Cursor cursor = db
                .query("Announcement", null, "title = ?", new String[] {title}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                announcement.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                announcement.setTag(cursor.getString(cursor.getColumnIndex("tag")));
                announcement.setCount(cursor.getString(cursor.getColumnIndex("count")));
                announcement.setDescribe(cursor.getString(cursor.getColumnIndex("describe")));
            } while (cursor.moveToNext());
        }
        return announcement;
    }

    public void deleteAnnouncement(String title){
        db.delete("Announcement", "title = ?", new String[]{title});
    }

    public void deleteMyFollow(String title){
        db.delete("MyFollow", "title = ?", new String[]{title});
    }
}