package com.example.comer.autosmslogin.db;

import android.content.Context;

import com.example.comer.autosmslogin.models.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by musta on 23.08.2016.
 */
public class DataBaseTransaction {
    Context context;

    public DataBaseTransaction(Context context) {
        this.context = context;
    }

    boolean checkUserName(String username) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<User> resultUser = realm.where(User.class).equalTo("username", username).findAll();
        if (resultUser.size() == 0) {
            return true;
        } else {
            return false;

        }
    }

    boolean addUser(User user) {
        try {
            RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
            Realm.setDefaultConfiguration(realmConfig);
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            User realmUser = realm.copyToRealm(user);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    boolean checkEMail(String username) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<User> resultUser = realm.where(User.class).equalTo("username", username).findAll();
        if (resultUser.get(0).getEMail() == true) {
            return true;
        } else {
            return false;

        }
    }

}
