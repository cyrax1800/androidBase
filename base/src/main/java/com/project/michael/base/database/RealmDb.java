package com.project.michael.base.database;

import android.content.Context;
import android.util.Log;

import com.project.michael.base.utils.Settings;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.log.RealmLog;

/**
 * Created by michael on 1/30/17.
 */

public class RealmDb {

    private static String TAG = "tmp-Realm";

    private static RealmConfiguration realmConfiguration;

    public static void SetUpRealmDatabase(Context context){
        Realm.init(context);

        if(Settings.getRealmDatabaseName().isEmpty())
            throw new ExceptionInInitializerError("RealmDatabaseName must be define in Settings.json file");

        RealmConfiguration.Builder builder = new RealmConfiguration.Builder()
                .name(Settings.getRealmDatabaseName())
                .schemaVersion(Settings.getRealmDatabaseSchemaVersion());

        realmConfiguration = builder.build();

        Realm.setDefaultConfiguration(realmConfiguration);

        RealmLog.setLevel(Log.VERBOSE);
    }

    public static void migrate(RealmMigration realmMigration){
        try {
            Realm.migrateRealm(realmConfiguration, realmMigration);
        }catch (Exception e){
            Log.e(TAG, "migrate: ", e.getCause());
        }
    }

    public static void initiateData(Realm.Transaction transaction){
        try {
            getRealm().executeTransaction(transaction);
        }catch (Exception e){
            Log.e(TAG, "migrate: ", e.getCause());
        }
    }

    public static Realm getRealm() {
        if(!Settings.isUsingRealmDatabase())
            throw new NullPointerException("Must set usingRealmDatabase true in settings.json file");

        return Realm.getDefaultInstance();
    }
}
