package com.example.order002;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{

    public static final String CREATE_USERDATA1="create table userData1(" +
            "id integer primary key autoincrement,"
            +"name text not null,"
            +"password text not null)";

    public static final String CREATE_ADMINDATA1="create table adminData1(" +
            "id integer primary key autoincrement,"
            +"name text not null,"
            +"password text not null)";

    public static final String CREATE_YOUHUIDATA1="create table youhuiData1(" +
            "id integer primary key autoincrement,"
            +"max double,"
            +"youhui double)";

    public static final String CREATE_FORMDATA1="create table formData1(" +
            "id integer primary key autoincrement,"
            +"user text not null,"
            +"sum double)";

    private Context mContext;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory,int version){
        super(context,name,cursorFactory,version);
        mContext=context;
    }

    public  void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USERDATA1);
        db.execSQL(CREATE_ADMINDATA1);
        db.execSQL(CREATE_YOUHUIDATA1);
        db.execSQL(CREATE_FORMDATA1);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //onCreate(db);
    }






}
