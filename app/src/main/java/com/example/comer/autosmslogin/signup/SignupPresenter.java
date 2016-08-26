package com.example.comer.autosmslogin.signup;

import android.app.Application;

import com.example.comer.autosmslogin.login.DatabaseInteractor;
import com.example.comer.autosmslogin.login.IDatabaseInteractor;
import com.example.comer.autosmslogin.models.User;
import com.example.comer.autosmslogin.services.SmsService;

/**
 * Created by comer on 23.08.2016.
 */
public class SignupPresenter implements ISignupPresenter{
    ISignupView view;
    IDatabaseInteractor databaseInteractor;
    User usera;
    public SignupPresenter(ISignupView view, Application application) {
        this.view = view;
        databaseInteractor = new DatabaseInteractor(application);
    }


    @Override
    public void clickSave(String username, String password) {

        if (username.isEmpty() || password.isEmpty()) {
            view.emptyAllert();
        }
        usera=databaseInteractor.checkSignUser(username);
        if (usera != null) {
            try {
                if (usera.getUsername().equals(username))
                {view.signFailed();}}
            catch (Exception e){}
        }



        else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.seteMail(false);
            databaseInteractor.addUser(user);
            SmsService.checkSms = false;
            view.goActivation(username);
            view.onSuccess();}
        }}
