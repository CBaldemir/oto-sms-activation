package com.example.comer.autosmslogin;

import android.app.Application;

import com.example.comer.autosmslogin.di.component.DaggerIApplicationComponent;
import com.example.comer.autosmslogin.di.component.IApplicationComponent;
import com.example.comer.autosmslogin.di.module.ApplicationModule;

/**
 * Created by musta on 23.08.2016.
 */
public class SmsApp extends Application {
    private IApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerIApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public IApplicationComponent getComponent() {
        return component;
    }
}
