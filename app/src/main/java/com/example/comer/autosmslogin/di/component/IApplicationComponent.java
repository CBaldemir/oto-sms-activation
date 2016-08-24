package com.example.comer.autosmslogin.di.component;

import com.example.comer.autosmslogin.di.module.ApplicationModule;
import com.example.comer.autosmslogin.login.DatabaseInteractor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by musta on 23.08.2016.
 */

@Singleton
@Component(modules = {ApplicationModule.class})

public interface IApplicationComponent {
    void inject(DatabaseInteractor databaseInteractor);
}
