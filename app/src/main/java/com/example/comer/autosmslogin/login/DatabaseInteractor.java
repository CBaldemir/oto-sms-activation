package com.example.comer.autosmslogin.login;

import android.app.Application;

import com.example.comer.autosmslogin.models.User;
import com.example.comer.autosmslogin.SmsApp;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by musta on 23.08.2016.
 */
public class DatabaseInteractor implements IDatabaseInteractor {

    @Inject
    Realm realm;

    public DatabaseInteractor(Application application) {
        ((SmsApp) application).getComponent().inject(this);

    }

    @Override
    public void addUser(User user) {

            realm.beginTransaction();
            User realmUser = realm.copyToRealm(user);
            realm.commitTransaction();


    }

    @Override
    public User checkUser(String username) {
        final User user = realm.where(User.class).equalTo("username", username).findFirst();
        return user;
    }

    @Override
    public boolean checkEMail(String username) {
        final User user = realm.where(User.class).equalTo("username", username).findFirst();
        return user.getEMail();
    }

    @Override
    public void setEmailTrue(String username) {
        final User user = realm.where(User.class).equalTo("username", username).findFirst();
        user.seteMail(true);
        realm.beginTransaction();
        User realmUser = realm.copyToRealm(user);
        realm.commitTransaction();
    }
}
