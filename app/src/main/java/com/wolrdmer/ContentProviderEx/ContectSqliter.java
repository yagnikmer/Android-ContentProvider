package com.wolrdmer.ContentProviderEx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContectSqliter extends SQLiteOpenHelper {
    public static final String DATABASE = "mer.db";
    public static final String TABLE_CONTACTS = "Contacts";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    static Context mContext;

    static String CREATE_TABLE_QUERY = "create table " + TABLE_CONTACTS +
            " (" + KEY_ID + " integer primary key," + KEY_NAME + " text)";
    static String DELETE_TABLE_QUERY = "drop table if exists " + TABLE_CONTACTS;

    public ContectSqliter(Context context) {
        super(context, DATABASE, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_QUERY);
        onCreate(db);
    }
}