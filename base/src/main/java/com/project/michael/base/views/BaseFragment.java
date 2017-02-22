package com.project.michael.base.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.project.michael.base.database.RealmDb;
import com.project.michael.base.utils.Settings;

import io.realm.Realm;

/**
 * Created by michael on 1/29/17.
 */

public class BaseFragment extends Fragment {
    public static final String TAG = "tmp-Fragment";
    private Realm realm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Settings.isUsingRealmDatabase())
            realm = RealmDb.getRealm();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(Settings.isUsingRealmDatabase())
            realm.close();
    }
}
