package com.project.michael.base.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by michael on 1/31/17.
 */

public class RealmModel extends RealmObject{
    private static int Increment = -1;

    @PrimaryKey
    private int id;

    public static int getIncrement(Realm realm, Class T) {
        if(Increment == -1){
            if(realm.where(T).findAll().size() == 0)
                Increment = 1;
            else
                Increment = realm.where(T).max("id").intValue();
        }
        Increment += 1;
        return Increment;
    }

}
