package com.project.michael.base.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by sunil on 20/1/16.
 */
public class PermissionsUtils {

    public static final int REQUEST_STORAGE     = 0;
    public static final int REQUEST_CAMERA      = 1;
    public static final int REQUEST_CONTACT     = 2;
    public static final int REQUEST_LOCATION    = 3;
    public static final int REQUEST_CALL_PHONE  = 4;

    public static final String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                        Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final String[] PERMISSION_CAMERA   = {Manifest.permission.CAMERA};
    public static final String[] PERMISSION_CONTACT  = {Manifest.permission.READ_CONTACTS};
    public static final String[] PERMISSION_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION};
    public static final String[] PERMISSION_CALL     = {Manifest.permission.CALL_PHONE};

    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static Boolean isPermissionGranted(Activity activity, String[] permission, boolean requestIfNotGranted){
        if(checkSelfPermission(activity,permission)){
            if(requestIfNotGranted){
                requestPermissions(activity, permission, getRequestCode(permission));
            }
        }else {
            return true;
        }
        return false;
    }

    private static int getRequestCode(String[] permission) {
        if      (permission == PERMISSIONS_STORAGE) return REQUEST_STORAGE;
        else if (permission == PERMISSION_CAMERA)   return REQUEST_CAMERA;
        else if (permission == PERMISSION_CONTACT)  return REQUEST_CONTACT;
        else if (permission == PERMISSION_LOCATION) return REQUEST_LOCATION;
        else if (permission == PERMISSION_CALL)     return REQUEST_CALL_PHONE;
        return 0;
    }

    public static Boolean checkSelfPermission(Activity activity, String[] permission){
        if      (permission == PERMISSIONS_STORAGE) return checkForStorage(activity);
        else if (permission == PERMISSION_CAMERA)   return checkForCamera(activity);
        else if (permission == PERMISSION_CONTACT)  return checkForContact(activity);
        else if (permission == PERMISSION_LOCATION) return checkForLocation(activity);
        else if (permission == PERMISSION_CALL)     return checkForCall(activity);
        return true;
    }

    public static Boolean shouldShowRequestPermission(Activity activity, String[] permission){
        if      (permission == PERMISSIONS_STORAGE) return shouldShowRequestForStoragePermission(activity);
        else if (permission == PERMISSION_CAMERA)   return shouldShowRequestForCameraPermission(activity);
        else if (permission == PERMISSION_CONTACT)  return shouldShowRequestForContactPermission(activity);
        else if (permission == PERMISSION_LOCATION) return shouldShowRequestForLocationPermission(activity);
        else if (permission == PERMISSION_CALL)     return shouldShowRequestForCallPermission(activity);
        return true;
    }

    public static boolean isGranted(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    // Check is Granted Per Permission
    public static boolean checkForStorage(Activity activity) {
        return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkForLocation(Activity activity) {
        return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkForCall(Activity activity) {
        return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkForCamera(Activity activity) {
        return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED);
    }

    public static boolean checkForContact(Activity activity) {
        return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED);
    }


    // Should Permission more or less feature
    public static boolean shouldShowRequestForLocationPermission(Activity activity) {
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION));
    }

    public static boolean shouldShowRequestForStoragePermission(Activity activity) {
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE));
    }

    public static boolean shouldShowRequestForCallPermission(Activity activity) {
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CALL_PHONE));
    }

    public static boolean shouldShowRequestForCameraPermission(Activity activity) {
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA));
    }

    public static boolean shouldShowRequestForContactPermission(Activity activity) {
        return (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_CONTACTS));
    }

    public static void showExplanation(final Activity activity, String title,
                                 String message,
                                 final String[] permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermissions(activity, permission, permissionRequestCode);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }
}
