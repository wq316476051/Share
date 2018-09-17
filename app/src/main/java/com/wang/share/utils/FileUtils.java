package com.wang.share.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

public class FileUtils {
    
    private static final String TAG = "FileUtils";

    private FileUtils() {}

    public static Uri getImageUri(Context context, String filePath) {
        Log.d(TAG, "getImageUri: filePath = " + filePath);
        if (filePath == null) {
            return null;
        }
        Uri baseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String _id = MediaStore.Images.Media._ID;
        String data = MediaStore.Images.Media.DATA;

        Cursor cursor = context.getContentResolver().query(baseUri, new String[] { _id },
                data + "=? ", new String[] { filePath }, null);

        Uri uri = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(_id));
                uri = Uri.withAppendedPath(baseUri, "" + id);
                Log.d(TAG, "getImageUri: uri (by query) = " + uri);
            }
            cursor.close();
        }

        if (uri == null) {
            ContentValues values = new ContentValues();
            values.put(data, filePath);
            uri = context.getContentResolver().insert(baseUri, values);
            Log.d(TAG, "getImageUri: uri (by query) = " + uri);
        }
        return uri;
    }

    public static Uri getAudioUri(Context context, String filePath) {
        Log.d(TAG, "getAudioUri: filePath = " + filePath);
        if (filePath == null) {
            return null;
        }
        Uri baseUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String _id = MediaStore.Audio.Media._ID;
        String data = MediaStore.Audio.Media.DATA;

        Cursor cursor = context.getContentResolver().query(baseUri, new String[]{ _id },
                data + "=?", new String[]{ filePath }, null);

        Uri uri = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(_id));
                uri = Uri.withAppendedPath(baseUri, String.valueOf(id));
                Log.d(TAG, "getAudioUri: uri (by query) = " + uri);
            }
            cursor.close();
        }

        if (uri == null) {
            ContentValues values = new ContentValues();
            values.put(data, filePath);
            uri = context.getContentResolver().insert(baseUri, values);
            Log.d(TAG, "getAudioUri: uri (by insert) = " + uri);
        }
        return uri;
    }

    public static Uri getVideoUri(Context context, String filePath) {
        Log.d(TAG, "getVideoUri: filePath = " + filePath);
        if (filePath == null) {
            return null;
        }
        Uri baseUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String _id = MediaStore.Video.Media._ID;
        String data = MediaStore.Video.Media.DATA;

        Cursor cursor = context.getContentResolver().query(baseUri, new String[] { _id },
                data + "=? ", new String[] { filePath }, null);

        Uri uri = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(_id));
                uri = Uri.withAppendedPath(baseUri, "" + id);
                Log.d(TAG, "getVideoUri: uri (by query) = " + uri);
            }
            cursor.close();
        }

        if (uri == null) {
            ContentValues values = new ContentValues();
            values.put(data, filePath);
            uri = context.getContentResolver().insert(baseUri, values);
            Log.d(TAG, "getVideoUri: uri (by insert) = " + uri);
        }
        return uri;
    }

    public static Uri getFileUri(Context context, String filePath) {
        Log.d(TAG, "getFileUri: filePath = " + filePath);
        if (filePath == null) {
            return null;
        }
        String volumeName = "external";
        Uri baseUri = MediaStore.Files.getContentUri(volumeName);
        String _id = MediaStore.Files.FileColumns._ID;
        String data = MediaStore.Files.FileColumns.DATA;

        Uri uri = null;
        Cursor cursor = context.getContentResolver().query(baseUri, new String[]{ _id },
                data + "=? ", new String[] { filePath }, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID));
                uri = MediaStore.Files.getContentUri(volumeName, id);
                Log.d(TAG, "getVideoUri: uri (by query) = " + uri);
            }
            cursor.close();
        }
        if (uri == null) {
            ContentValues values = new ContentValues();
            values.put(data, filePath);
            uri = context.getContentResolver().insert(baseUri, values);
            Log.d(TAG, "getFileUri: uri (by insert) = " + uri);
        }
        return uri;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String[] projection = { MediaStore.Files.FileColumns.DATA };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }
}
