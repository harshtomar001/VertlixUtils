package com.vertlix.utils.file;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

public class GetFileName {

    public static String getFileName(Context context, Uri uri) {
        String fileName = "Unknown file";

        if (uri == null) return fileName;

        Cursor cursor = context.getContentResolver()
                .query(uri, null, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        fileName = cursor.getString(nameIndex);
                    }
                }
            } finally {
                cursor.close();
            }
        } else {

            String path = uri.getPath();
            if (path != null) {
                int cut = path.lastIndexOf('/');
                fileName = (cut != -1) ? path.substring(cut + 1) : path;
            }
        }

        return fileName;
    }
}
