package com.wolrdmer.ContentProviderEx;

import android.content.UriMatcher;
import android.net.Uri;
import android.util.Log;

public class URITest {

    String authority = "com.mer.url";
    String path = "table";
    int code = 100;

    String URL = "content://com.mer.url/table";
    Uri uri = Uri.parse(URL);
    UriMatcher uriMatcher;

    public URITest() {
        /**
         * pass default value
         * if urimatch.uri(uri) uri wrong return return default value
         */
        uriMatcher = new UriMatcher(100);
        uriMatcher.addURI(authority, path, 200);
        uriMatcher.addURI(authority, path + "/#", 300);
    }

    public void test() {
        int code = uriMatcher.match(Uri.parse(""));

        Log.d("AppFlow", "Uri : " + uri.toString() + "  Code : " + code);
    }


}
