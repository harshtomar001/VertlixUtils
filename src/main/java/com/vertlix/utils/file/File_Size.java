package com.vertlix.utils.file;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import java.io.File;

public class File_Size {

    public static long getFileSize(Context context, Uri uri) {

        if (uri == null) return 0;

        //  for  file:// URIs
        if ("file".equalsIgnoreCase(uri.getScheme())) {

            File file = new File(uri.getPath());

            return file.exists() ? file.length() : 0;
        }

        //  for  content:// URIs
        if ("content".equalsIgnoreCase(uri.getScheme())) {

            Cursor cursor = null;

            try {
                cursor = context.getContentResolver()
                        .query(uri, new String[]{OpenableColumns.SIZE},
                                null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                    if (sizeIndex != -1) {
                        return cursor.getLong(sizeIndex);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) cursor.close();
            }
        }

        return 0;
    }

}