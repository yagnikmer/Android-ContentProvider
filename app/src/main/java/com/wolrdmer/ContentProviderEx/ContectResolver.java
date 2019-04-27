package com.wolrdmer.ContentProviderEx;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;


public class ContectResolver {

    public static final Uri uri = Uri.parse("content://" + "MY_AUTHORITY" + "/" + "SHARED_DATA");
    public static final String KEY_NAME = "name";

    private ContentResolver mResolver;
    private Context mContext;

    public ContectResolver(Context context) {
        this.mContext = context;
        mResolver = context.getContentResolver();
    }

    public void insert(String name) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        mResolver.insert(uri, values);
    }

    public void update(String oldName, String newName) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, newName);
        String where = KEY_NAME + "=?";
        String[] selectionArgs = {oldName};
        mResolver.update(uri, values, where, selectionArgs);
    }

    public void delete(String name) {
        String where = KEY_NAME + "=?";
        String selectionArgs[] = {name};
        mResolver.delete(uri, where, selectionArgs);
    }

    public void displayAll() {
        Cursor cursor = mResolver.query(uri, null, null, null, null);
        String records = "";
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            records = records + "(" + id + " | " + name + "), ";
            cursor.moveToNext();
        }
        Toast.makeText(mContext, records, Toast.LENGTH_SHORT).show();
    }
}