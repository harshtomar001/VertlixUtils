package com.vertlix.utils.pdf;

import android.content.Context;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Page_Count {
    public static int getPdfPageCount(Context context, Uri uri) {
        ParcelFileDescriptor pfd = null;
        PdfRenderer renderer = null;

        try {
            pfd = context.getContentResolver()
                    .openFileDescriptor(uri, "r");

            if (pfd == null) return 0;

            renderer = new PdfRenderer(pfd);
            return renderer.getPageCount();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        } finally {
            try {
                if (renderer != null) renderer.close();
                if (pfd != null) pfd.close();
            } catch (Exception ignored) {}
        }
    }

    public static int getDocxPageCount(Context context,Uri uri) {
            int pages = 0;
            try {
                InputStream inputStream = context.getContentResolver().openInputStream(uri);
                ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                ZipEntry entry;

                while ((entry = zipInputStream.getNextEntry()) != null) {
                    if (entry.getName().equals("docProps/app.xml")) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream));
                        String line;

                        while ((line = reader.readLine()) != null) {

                            if (line.contains("<Pages>")) {

                                pages = Integer.parseInt(
                                        line.replaceAll(".*<Pages>(\\d+)</Pages>.*", "$1")
                                );
                                break;
                            }
                        }
                    }
                }

                zipInputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return pages;
        }


    public static int getPptxSlideCount(Context context, Uri uri){
        int slides = 0;

        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);

            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {

                if (entry.getName().startsWith("ppt/slides/slide")) {
                    slides++;
                }

            }

            zipInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return slides;
    }


}
