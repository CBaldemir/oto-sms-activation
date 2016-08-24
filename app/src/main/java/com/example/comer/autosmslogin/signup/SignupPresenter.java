package com.example.comer.autosmslogin.signup;

import android.app.Application;

import com.example.comer.autosmslogin.login.DatabaseInteractor;
import com.example.comer.autosmslogin.login.IDatabaseInteractor;
import com.example.comer.autosmslogin.models.User;
import com.example.comer.autosmslogin.SmsApp;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by comer on 23.08.2016.
 */
public class SignupPresenter implements ISignupPresenter{
    ISignupView view;
    @Inject
    Realm realm;
    IDatabaseInteractor databaseInteractor;

    public SignupPresenter(ISignupView view, Application application) {
        this.view = view;
        ((SmsApp) application).getComponent().inject(this);
        databaseInteractor = new DatabaseInteractor(application);
    }

    @Override

    public void Databasecontrol() {

    }

    @Override
    public ArrayList<String> kayit() {
        return null;
    }

    @Override
    public void clickSave(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.seteMail(false);
        databaseInteractor.addUser(user);
        view.goActivation(username);
    }
}
