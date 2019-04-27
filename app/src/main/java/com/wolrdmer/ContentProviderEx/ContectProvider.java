package com.wolrdmer.ContentProviderEx;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ContectProvider extends ContentProvider {

    public static final String TABLE_CONTACTS = "Contacts";
    SQLiteDatabase mDatabase;

    @Override
    public boolean onCreate() {
        ContectSqliter sqliter = new ContectSqliter(getContext());
        mDatabase = sqliter.getWritableDatabase();
        return (mDatabase != null);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_CONTACTS);
        Cursor cursor = queryBuilder.query(mDatabase, projection, selection, selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mDatabase.insert(TABLE_CONTACTS, null, values);
        Uri content_uri = ContentUris.withAppendedId(uri, id);
        getContext().getContentResolver().notifyChange(content_uri, null);
        return content_uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int id = mDatabase.delete(TABLE_CONTACTS, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return id;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int id = mDatabase.update(TABLE_CONTACTS, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return id;
    }

}
