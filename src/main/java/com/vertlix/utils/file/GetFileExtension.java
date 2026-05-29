package com.vertlix.utils.file;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class GetFileExtension {

    public static String getFileExtension(Context context, Uri uri) {
        String mimeType = context.getContentResolver().getType(uri);

        if (mimeType == null) return "";

        String extension = MimeTypeMap.getSingleton()
                .getExtensionFromMimeType(mimeType);
        if (extension.isEmpty()) {
            String path = uri.getPath();
            if (path != null && path.contains(".")) {
                extension = path.substring(path.lastIndexOf(".") + 1);
            }
        }

        return extension != null ? extension : "";
    }
}
