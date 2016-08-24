package com.example.comer.autosmslogin.activation;

import android.app.Application;

import com.example.comer.autosmslogin.login.DatabaseInteractor;
import com.example.comer.autosmslogin.login.IDatabaseInteractor;
import com.example.comer.autosmslogin.SmsApp;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by comer on 23.08.2016.
 */
public class ActivationSmsPresenter implements IActivationSmsPresenter {
    @Inject
    Realm realm;
    IActivationSmsView activationSmsView;
    IDatabaseInteractor databaseInteractor;
    String username;

    public ActivationSmsPresenter(IActivationSmsView activationSmsView, Application application,String username) {
        this.activationSmsView = activationSmsView;
        ((SmsApp) application).getComponent().inject(this);
        databaseInteractor= new DatabaseInteractor(application);
        this.username=username;
    }

    @Override
    public void ActivationSmsSubmit() {

    }

    @Override
    public void incomingMessage(String msg) {
        if (msg.equals("000000"))
        {
            databaseInteractor.setEmailTrue(username);
            activationSmsView.saveSucces();
        }
    }


}