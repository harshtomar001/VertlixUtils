package com.vertlix.utils.file;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class SaveFile {

    public static void savePDF(Context context, Uri uri, String fileName,int pageCount,String fileSize){

        try {
            // Open input stream from internal PDF Uri
            InputStream inputStream = context.getContentResolver().openInputStream(uri);

            // Prepare to save in Downloads using MediaStore
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            values.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

            ContentResolver resolver = context.getContentResolver();
            Uri downloadUri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);

            if (downloadUri != null && inputStream != null) {
                OutputStream outputStream = resolver.openOutputStream(downloadUri);

                byte[] buffer = new byte[8192];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

                // to store the list of created file in the app storage


                SharedPreferences prefs = context.getSharedPreferences("files", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                Gson gson = new Gson();

                String json = prefs.getString("recent_files", null);
                Type type = new TypeToken<ArrayList<RecentFileItem>>(){}.getType();

                ArrayList<RecentFileItem> list = gson.fromJson(json, type);

                if (list == null) list = new ArrayList<>();

                String pdfDate= FormattedDate.date();

                RecentFileItem newFile = new RecentFileItem(fileName,fileSize,pdfDate ,pageCount, uri.toString());


                list.add(0, newFile);

                editor.putString("recent_files", gson.toJson(list));
                editor.apply();


                Toast.makeText(context, "Saved to Downloads", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Failed to save PDF", Toast.LENGTH_SHORT).show();
        }
    }

    public static void saveImageFromUri(Context context, Uri sourceUri, String fileName, String format) {
        try {
            String mimeType;
            switch (format.toLowerCase()) {
                case "png":
                    mimeType = "image/png";
                    if (!fileName.endsWith(".png")) fileName += ".png";
                    break;
                case "webp":
                    mimeType = "image/webp";
                    if (!fileName.endsWith(".webp")) fileName += ".webp";
                    break;
                case "bmp":
                    mimeType = "image/bmp";
                    if (!fileName.endsWith(".bmp")) fileName += ".bmp";
                    break;
                case "jpeg":
                case "jpg":
                default:
                    mimeType = "image/jpeg";
                    if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg"))
                        fileName += ".jpg";
                    break;
            }

            ContentResolver resolver = context.getContentResolver();


            InputStream inputStream = resolver.openInputStream(sourceUri);
            if (inputStream == null) {
//                Toast.makeText(context, "Failed to read image", Toast.LENGTH_SHORT).show();
                return;
            }


            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

            Uri destUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            if (destUri == null) {
//                Toast.makeText(context, "Failed to create file", Toast.LENGTH_SHORT).show();
                return;
            }


            OutputStream outputStream = resolver.openOutputStream(destUri);
            if (outputStream == null) {
//                Toast.makeText(context, "Failed to write image", Toast.LENGTH_SHORT).show();
                return;
            }


            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

//            Toast.makeText(context, "Saved in Pictures", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }



}

