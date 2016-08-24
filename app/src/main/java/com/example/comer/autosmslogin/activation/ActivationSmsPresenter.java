package com.example.comer.autosmslogin.activation;

import android.app.Application;

import com.example.comer.autosmslogin.login.DatabaseInteractor;
import com.example.comer.autosmslogin.login.IDatabaseInteractor;

/**
 * Created by comer on 23.08.2016.
 */
public class ActivationSmsPresenter implements IActivationSmsPresenter {

    IActivationSmsView activationSmsView;
    IDatabaseInteractor databaseInteractor;

    public ActivationSmsPresenter(IActivationSmsView activationSmsView, Application application) {
        this.activationSmsView = activationSmsView;
        databaseInteractor= new DatabaseInteractor(application);

    }


    @Override
    public void incomingMessage(String msg, String username) {
        if (msg.equals("000000"))
        {
            databaseInteractor.setEmailTrue(username);
            activationSmsView.saveSucces();
        } else {
            activationSmsView.wrongMsg();
        }
    }


}