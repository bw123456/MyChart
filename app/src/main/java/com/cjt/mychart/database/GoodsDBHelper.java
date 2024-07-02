package com.cjt.mychart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cjt.mychart.lean.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class GoodsDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "GoodsDBHelper";
    private static final String DB_NAME = "goods.db"; // 数据库的名称
    private static final int DB_VERSION = 1; // 数据库的版本号
    private static GoodsDBHelper mHelper = null; // 数据库帮助器的实例
    private SQLiteDatabase mDB = null; // 数据库的实例
    private static final String TABLE_NAME = "goods_info"; // 表的名称

    public GoodsDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public GoodsDBHelper(Context context,int version){
        super(context,DB_NAME,null,version);
    }

    public static GoodsDBHelper getInstance(Context context,int version){
        if (version >0 && mHelper == null){
            mHelper = new GoodsDBHelper(context,version);
        }else {
            mHelper = new GoodsDBHelper(context);
        }
        return mHelper;
    }

    public SQLiteDatabase openReadLink(){
        if (mDB == null || !mDB.isOpen()){
            mDB = mHelper.getReadableDatabase();
        }
        return mDB;
    }

    public SQLiteDatabase openWriteLink(){
        if (mDB == null || !mDB.isOpen()){
            mDB = mHelper.getWritableDatabase();

        }
        return mDB;
    }

    public void closeLink(){
        if (mDB != null && mDB.isOpen()){
            mDB.close();
            mDB = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        String drop_sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        Log.d(TAG, "drop_sql:" + drop_sql);
        db.execSQL(drop_sql);
        String create_sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "name VARCHAR NOT NULL," + "desc VARCHAR NOT NULL,"
                + "price FLOAT NOT NULL," + "pic_path VARCHAR NOT NULL" + ");";
        Log.d(TAG, "create_sql:" + create_sql);
        db.execSQL(create_sql); // 执行完
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int delete(String condition){
        return mDB.delete(TABLE_NAME,condition,null);
    }

    public int deleteAll(){
        return mDB.delete(TABLE_NAME,"1=1",null);
    }
    public long insert(GoodsInfo info){
        List<GoodsInfo> infoList = new ArrayList<GoodsInfo>();
        infoList.add(info);
        return insert(infoList);
    }
    public long insert(List<GoodsInfo> infoList) {
        long result = -1;
        for (GoodsInfo info:infoList){
            if (info.rowid > 0){
                String condition = String.format("rowid='%d'",info.rowid);
                update(info,condition);
                result = info.rowid;
                continue;
            }
            ContentValues cv = new ContentValues();
            cv.put("name",info.name);
            cv.put("desc",info.desc);
            cv.put("price",info.price);
            cv.put("pic_path",info.pic_path);

            result = mDB.insert(TABLE_NAME,"",cv);
            if (result == -1){
                return  result;
            }
        }
        return result;
    }
    public int update(GoodsInfo info, String condition){
        ContentValues cv = new ContentValues();
        cv.put("name", info.name);
        cv.put("desc", info.desc);
        cv.put("price", info.price);
        cv.put("pic_path", info.pic_path);
        // 执行更新记录动作，该语句返回更新的记录数量
        return mDB.update(TABLE_NAME, cv, condition, null);
    }

    public int update(GoodsInfo info) {
        // 执行更新记录动作，该语句返回更新的记录数量
        return update(info, "rowid=" + info.rowid);
    }
    public List<GoodsInfo> query(String condition) {
        String sql = String.format("select rowid,_id,name,desc,price,pic_path" +
                " from %s where %s;", TABLE_NAME, condition);
        Log.d(TAG, "query sql: " + sql);
        List<GoodsInfo> infoList = new ArrayList<GoodsInfo>();
        // 执行记录查询动作，该语句返回结果集的游标
        Cursor cursor = mDB.rawQuery(sql, null);
        // 循环取出游标指向的每条记录
        while (cursor.moveToNext()) {
            GoodsInfo info = new GoodsInfo();
            info.rowid = cursor.getLong(0);
            info.xuhao = cursor.getInt(1);
            info.name = cursor.getString(2);
            info.desc = cursor.getString(3);
            info.price = cursor.getFloat(4);
            info.pic_path = cursor.getString(5);
            infoList.add(info);
        }
        cursor.close(); // 查询完毕，关闭数据库游标
        return infoList;
    }

    // 根据行号查询指定记录
    public GoodsInfo queryById(long rowid) {
        GoodsInfo info = null;
        List<GoodsInfo> infoList = query(String.format("rowid='%d'", rowid));
        if (infoList.size() > 0) {
            info = infoList.get(0);
        }
        return info;
    }
}
