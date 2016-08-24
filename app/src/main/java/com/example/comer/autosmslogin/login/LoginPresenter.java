package com.example.comer.autosmslogin.login;

import android.app.Application;

import com.example.comer.autosmslogin.models.User;
import com.example.comer.autosmslogin.SmsApp;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by comer on 23.08.2016.
 */
public class LoginPresenter implements ILoginPresenter {
    ILoginView view;
    @Inject
    Realm realm;
    IDatabaseInteractor databaseInteractor;

    public LoginPresenter(ILoginView view, Application application) {
        this.view = view;
        ((SmsApp) application).getComponent().inject(this);
        databaseInteractor = new DatabaseInteractor(application);
    }

    @Override
    public void Databasecontrol() {

    }

    @Override
    public void clickSignUp() {
        view.signUp();
    }

    @Override
    public void clickLogIn(String username, String password) {
        User user;
        if (username.isEmpty() || password.isEmpty()) {
            view.toast("lütfen tüm alanları doldurunuz");
        } else {
            user = databaseInteractor.checkUser(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    if(databaseInteractor.checkEMail(username))
                    {
                        view.toast("giriş başarılı");
                    }
                    else{

                    }
                } else {
                    view.toast("şifre yanlış");
                }
            } else {
                view.toast("kullanıcı bulunamadı lütfen kayıt olunuz");
            }
        }
    }
}
