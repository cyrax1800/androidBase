package com.project.michael.base;

import android.app.Application;
import android.util.Log;

import com.project.michael.base.api.APIManager;
import com.project.michael.base.database.RealmDb;
import com.project.michael.base.database.SharedPref;
import com.project.michael.base.utils.FileUtils;
import com.project.michael.base.utils.GsonUtils;
import com.project.michael.base.utils.Settings;

/**
 * Created by michael on 1/29/17.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Settings.getInstance().configureAppSetting(
                GsonUtils.getObjectFromJson(
                        FileUtils.loadSettingsJsonFile(getApplicationContext()),
                        Settings.class
                ));

        if(Settings.isUsingRealmDatabase())
            RealmDb.SetUpRealmDatabase(getApplicationContext());

        if(Settings.isUsingSharedPreference())
            SharedPref.getInstance().SetUpSharedPreference(getApplicationContext());

        if(Settings.isUsingRetrofitAPI())
            APIManager.SetUpRetrofit();
    }
}
