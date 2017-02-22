package com.project.michael.base.utils;

/**
 * Created by michael on 1/29/17.
 */

public class Settings {
    private static Settings instance;
    private Boolean usingRealmDatabase = true;
    private String realmDatabaseName = "";
    private int realmDatabaseSchemaVersion = 0;
    private Boolean usingSharedPreference = false;
    private String sharedPreferenceName =  "";
    private Boolean usingRetrofitAPI = false;
    private String retrofitAPIUrl = "";

    private Settings(){ }

    public void configureAppSetting(Settings settings){
        instance = settings;
    }

    public static Boolean isUsingRealmDatabase() {
        return getInstance().usingRealmDatabase;
    }

    public static Boolean isUsingSharedPreference() {
        return getInstance().usingSharedPreference;
    }

    public static Boolean isUsingRetrofitAPI() {
        return getInstance().usingRetrofitAPI;
    }

    public static String getRealmDatabaseName() {
        return getInstance().realmDatabaseName;
    }

    public static String getSharedPreferenceName() {
        return getInstance().sharedPreferenceName;
    }

    public static String getRetrofitAPIUrl() {
        return getInstance().retrofitAPIUrl;
    }

    public static int getRealmDatabaseSchemaVersion() {
        return getInstance().realmDatabaseSchemaVersion;
    }

    public synchronized static Settings getInstance() {
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "AlCustomizationSettings{" +
                "usingRealmDatabase=" + usingRealmDatabase +
                ", realmDatabaseName='" + realmDatabaseName + "\'" +
                ", realmDatabaseSchemaVersion=" + realmDatabaseSchemaVersion +
                ", usingSharedPreference=" + usingSharedPreference +
                ", sharedPreferenceName='" + sharedPreferenceName + "\'" +
                ", usingRetrofitAPI=" + usingRetrofitAPI +
                ", retrofitAPIUrl='" + retrofitAPIUrl + "\'" +
                '}';
    }
}
