package com.project.michael.base.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * Created by michael on 1/29/17.
 */

public class FileUtils {

    public static String loadSettingsJsonFile(Context context) {
        BufferedReader br = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(
                    "settings.json"), "UTF-8"));
            String line;
            if (br != null) {
                while ((line = br.readLine()) != null) {
                    stringBuffer.append(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }  finally{
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    /**
     * Gets the extension of a file name, like ".png" or ".jpg".
     *
     * @param uri
     * @return Extension including the dot("."); "" if there is no extension;
     * null if uri was null.
     */
    public static String getExtension(String uri) {
        if (uri == null) {
            return null;
        }

        int dot = uri.lastIndexOf(".");
        if (dot >= 0) {
            return uri.substring(dot);
        } else {
            // No extension.
            return "";
        }
    }

    public static String getFileFormat(String fileName) {

        String fileType = "";

        int i = fileName.lastIndexOf('.');

        if (i > 0) {
            fileType = fileName.substring(i + 1);
        }
        return fileType;
    }

    /**
     * @return Whether the URI is a local one.
     */
    public static boolean isLocal(String url) {
        if (url != null && !url.startsWith("http://") && !url.startsWith("https://")) {
            return true;
        }
        return false;
    }

    /**
     * Convert File into Uri.
     *
     * @param file
     * @return uri
     */
    public static Uri getUri(File file) {
        if (file != null) {
            return Uri.fromFile(file);
        }
        return null;
    }

    /**
     * Returns the path only (without file name).
     *
     * @param file
     * @return
     */
    public static File getPathWithoutFilename(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                // no file to be split off. Return everything
                return file;
            } else {
                String filename = file.getName();
                String filepath = file.getAbsolutePath();

                // Construct path without file name.
                String pathwithoutname = filepath.substring(0,
                        filepath.length() - filename.length());
                if (pathwithoutname.endsWith("/")) {
                    pathwithoutname = pathwithoutname.substring(0, pathwithoutname.length() - 1);
                }
                return new File(pathwithoutname);
            }
        }
        return null;
    }

    /**
     * Get the file size in a human-readable string.
     *
     * @param size
     * @return
     * @author paulburke
     */
    public static String getReadableFileSize(int size) {
        final int BYTES_IN_KILOBYTES = 1024;
        final DecimalFormat dec = new DecimalFormat("###.#");
        final String KILOBYTES = " KB";
        final String MEGABYTES = " MB";
        final String GIGABYTES = " GB";
        float fileSize = 0;
        String suffix = KILOBYTES;

        if (size > BYTES_IN_KILOBYTES) {
            fileSize = size / BYTES_IN_KILOBYTES;
            if (fileSize > BYTES_IN_KILOBYTES) {
                fileSize = fileSize / BYTES_IN_KILOBYTES;
                if (fileSize > BYTES_IN_KILOBYTES) {
                    fileSize = fileSize / BYTES_IN_KILOBYTES;
                    suffix = GIGABYTES;
                } else {
                    suffix = MEGABYTES;
                }
            }
        }
        return String.valueOf(dec.format(fileSize) + suffix);
    }

    /**
     * @param uri
     * @param context
     * @return
     */
    public static String getFileName(Context context, Uri uri) {

        String fileName = null;
        Cursor returnCursor =
                context.getContentResolver().query(uri, null, null, null, null);
        if (returnCursor != null && returnCursor.moveToFirst()) {
            int columnIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            fileName = returnCursor.getString(columnIndex);
        }

        return fileName;
    }

    public static String getSize(Context context, Uri uri) {

        String sizeInMB = null;
        Cursor returnCursor =
                context.getContentResolver().query(uri, null, null, null, null);

        if (returnCursor != null && returnCursor.moveToFirst()) {

            int columnIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            Long fileSize = returnCursor.getLong(columnIndex);
            if (fileSize < 1024) {
                sizeInMB = (int) (fileSize / (1024 * 1024)) + " B";

            } else if (fileSize < 1024 * 1024) {
                sizeInMB = (int) (fileSize / (1024)) + " KB";
            } else {
                sizeInMB = (int) (fileSize / (1024 * 1024)) + " MB";
            }
        }

        return sizeInMB;
    }
}
