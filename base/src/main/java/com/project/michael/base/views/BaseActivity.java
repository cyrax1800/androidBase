package com.project.michael.base.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.project.michael.base.database.RealmDb;
import com.project.michael.base.utils.Settings;

import io.realm.Realm;

/**
 * Created by michael on 1/29/17.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "tmp-Activity";
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if(Settings.isUsingRealmDatabase())
            realm = RealmDb.getRealm();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(Settings.isUsingRealmDatabase())
            realm.close();
    }

    public void changeActivityForResult(Context context, Class clazz, int requestCode, Bundle option){
        Intent i = new Intent(context,clazz);
        startActivityForResult(i,requestCode,option);
    }

    public void changeActivityForResult(Context context, Class clazz, int requestCode){
        Intent i = new Intent(context,clazz);
        startActivityForResult(i,requestCode);
    }
}
