package vn.edu.fpt.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "quizzpractice.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override//van dung sql  luu trong sql lich su 10 dap an moi nhat
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users(username text primary key, password text,name text)");
        db.execSQL("create table if not exists story(id Integer primary key autoincrement ,tenDe text, cauDung text, cauSai text, tongCau text )");
        db.execSQL("create table if not exists resultchecked(id Integer primary key autoincrement,cauhoi text, lanthi INTEGER, dapanchon text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists story");
        db.execSQL("drop table if exists resultchecked");
        onCreate(db);
    }

    public Boolean insertData(String username, String password, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("name", name);
        long result = db.insert("users", null, values);
        return result != -1;
    }

    public Boolean insertDataChecked(String answer, String question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dapanchon", answer);
        values.put("cauhoi", question);
        long result = db.insert("resultchecked", null, values);
        return result != -1;
    }

    public Boolean insertHistory(String storyid, String dapan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("story_id",storyid );
        values.put("dapan", dapan);
        long result = db.insert("history", null, values);
        return result != -1;
    }

    public Boolean insertDataStory(String tenDe, String cauDung, String cauSai, String tongCau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDe", tenDe);
        values.put("cauDung", cauDung);
        values.put("cauSai", cauSai);
        values.put("tongCau", tongCau);
        long result = db.insert("story", null, values);
        return result != -1;
    }

    public void queryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        onCreate(db);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public Cursor getResultCheckedData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", "lanthi", "dapanchon"};
        Cursor cursor = db.query("resultchecked", columns, null, null, null, null, null);
        return cursor;
    }
}
