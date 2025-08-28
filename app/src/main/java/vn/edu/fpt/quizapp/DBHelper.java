package vn.edu.fpt.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "quizzpractice.db";
    // Tăng version mỗi khi thay đổi cấu trúc bảng
    private static final int DB_VERSION = 4;

    public DBHelper(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    @Override//van dung sql  luu trong sql lich su 10 dap an moi nhat
    public void onCreate(SQLiteDatabase db) {
        // Thêm cột timestamp để lưu thời gian theo UnixTime (giây)
        db.execSQL("create table if not exists story(id Integer primary key autoincrement ,tenDe text, cauDung text, cauSai text, tongCau text, timestamp INTEGER)");
        db.execSQL("create table if not exists resultchecked(id Integer primary key autoincrement, tenDe text, cauhoi INTEGER, lanthi INTEGER, dapanchon text, dapandung text, timestamp INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists story");
        db.execSQL("drop table if exists resultchecked");
        onCreate(db);
    }

    public boolean insertDataChecked(String tenDe,int cauhoi,int lanthi,String dapanchon,String dapandung){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put("tenDe",tenDe);
        v.put("cauhoi",cauhoi);
        v.put("lanthi",lanthi);
        v.put("dapanchon",dapanchon);
        v.put("dapandung",dapandung);
        v.put("timestamp", System.currentTimeMillis() / 1000); // UnixTime giây
        return db.insert("resultchecked",null,v)!=-1;
    }

    public int getMaxLanThi(String tenDe){
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT MAX(lanthi) FROM resultchecked WHERE tenDe=?",new String[]{tenDe});
        int max=0;if(c.moveToFirst()) max=c.getInt(0);c.close();return max;
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
        values.put("timestamp", System.currentTimeMillis() / 1000); // UnixTime giây
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
