package com.project.michael.base.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.project.michael.base.utils.Settings;

import java.lang.ref.WeakReference;

/**
 * Created by michael on 1/30/17.
 */

public class SharedPref {

    private static String TAG = "tmp-SharedPref";

    private static SharedPref mInstance;

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    public void SetUpSharedPreference(Context context){
        if(Settings.getRealmDatabaseName().isEmpty())
            throw new ExceptionInInitializerError("sharedPreferenceName must be define in Settings.json file");

        sharedpreferences = context.getSharedPreferences(Settings.getRealmDatabaseName(),Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public static void save(String key,String value){
        getInstance().editor.putString(key,value);
        getInstance().editor.commit();
    }

    public static void save(String key,Boolean value){
        getInstance().editor.putBoolean(key,value);
        getInstance().editor.commit();
    }

    public static void save(String key,int value){
        getInstance().editor.putInt(key,value);
        getInstance().editor.commit();
    }

    public static void save(String key,float value){
        getInstance().editor.putFloat(key,value);
        getInstance().editor.commit();
    }

    public static String getValueString(String key){
        return getInstance().sharedpreferences.getString(key,"");
    }

    public static int getValueint(String key){
        return getInstance().sharedpreferences.getInt(key,0);
    }

    public static boolean getValueBool(String key){
        return getInstance().sharedpreferences.getBoolean(key,false);
    }

    public static SharedPref getInstance(){
        if(mInstance == null){
            mInstance = new SharedPref();
        }
        if(mInstance.sharedpreferences == null)
            throw new NullPointerException("sharedPreference has not defined");
        return mInstance;
    }
}
