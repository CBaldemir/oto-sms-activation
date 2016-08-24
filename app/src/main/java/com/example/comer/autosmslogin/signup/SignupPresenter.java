package com.example.comer.autosmslogin.signup;

import android.app.Application;

import com.example.comer.autosmslogin.SmsApp;
import com.example.comer.autosmslogin.login.DatabaseInteractor;
import com.example.comer.autosmslogin.login.IDatabaseInteractor;
import com.example.comer.autosmslogin.models.User;

/**
 * Created by comer on 23.08.2016.
 */
public class SignupPresenter implements ISignupPresenter{
    ISignupView view;
    IDatabaseInteractor databaseInteractor;

    public SignupPresenter(ISignupView view, Application application) {
        this.view = view;
        databaseInteractor = new DatabaseInteractor(application);
    }


    @Override
    public void clickSave(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            view.emptyAllert();
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.seteMail(false);
            databaseInteractor.addUser(user);
            SmsApp.checkSms = false;
            view.goActivation(username);
            view.onSuccess();

        }

    }
}
