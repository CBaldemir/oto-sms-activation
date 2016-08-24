package com.example.comer.autosmslogin.di.module;

import com.example.comer.autosmslogin.SmsApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by musta on 23.08.2016.
 */
@Module
public class ApplicationModule {

    private final SmsApp smsApp;

    public ApplicationModule(SmsApp smsApp) {
        this.smsApp = smsApp;
    }

    @Provides
    @Singleton
    SmsApp providesApplication() {
        return this.smsApp;
    }

    @Provides
    @Singleton
    Realm getRealm(SmsApp smsApp) {
        RealmConfiguration realmConfig = new RealmConfiguration
                .Builder(smsApp.getApplicationContext())
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();
        return realm;
    }

}
