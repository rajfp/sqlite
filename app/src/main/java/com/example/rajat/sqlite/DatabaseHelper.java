package com.example.rajat.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rajat on 22-May-17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String col_1="ID";
    public static final String col_2="NAME";
    public static final String col_3="SURNAME";
    public static final String col_4="MARKS";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+ TABLE_NAME +" ( "+col_1+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+col_2+ " TEXT, "+col_3+ " TEXT, "+col_4+ " INTEGER "+" ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String surname,String marks)
    {
        SQLiteDatabase  db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,surname);
        contentValues.put(col_4,marks);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public Cursor getAllData()
    {
        SQLiteDatabase  db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String name,String surname,String marks)
    {
        SQLiteDatabase  db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,surname);
        contentValues.put(col_4,marks);
        db.update(TABLE_NAME,contentValues, " ID = ?",new String[] {id});
        return true;
    }
    public Integer delete(String id)
    {

        SQLiteDatabase  db=this.getWritableDatabase();
       return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
}
