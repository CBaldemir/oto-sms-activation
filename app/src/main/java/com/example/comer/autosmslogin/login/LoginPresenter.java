package com.example.comer.autosmslogin.login;

import android.app.Application;

import com.example.comer.autosmslogin.models.User;
import com.example.comer.autosmslogin.services.SmsService;

/**
 * Created by comer on 23.08.2016.
 */
public class LoginPresenter implements ILoginPresenter {
    ILoginView view;

    IDatabaseInteractor databaseInteractor;

    public LoginPresenter(ILoginView view, Application application) {
        this.view = view;
        databaseInteractor = new DatabaseInteractor(application);
    }


    @Override
    public void clickSignUp() {
        view.signUp();
    }

    @Override
    public void clickLogIn(String username, String password) {
        User user;
        if (username.isEmpty() || password.isEmpty()) {
            view.fiilAllError();
        } else {
            user = databaseInteractor.checkUser(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    if(databaseInteractor.checkEMail(username))
                    {
                        view.trueAcces();
                    }
                    else{
                        view.setEMailTrue(username);
                        SmsService.checkSms = false;
                    }
                } else {
                    view.accesError();
                }
            } else {
                view.accesError();
            }
        }
    }
}
