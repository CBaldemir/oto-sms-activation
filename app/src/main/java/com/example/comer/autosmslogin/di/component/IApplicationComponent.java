package com.example.comer.autosmslogin.di.component;

import com.example.comer.autosmslogin.activation.ActivationSmsPresenter;
import com.example.comer.autosmslogin.login.DatabaseInteractor;
import com.example.comer.autosmslogin.login.LoginPresenter;
import com.example.comer.autosmslogin.signup.SignupPresenter;
import com.example.comer.autosmslogin.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by musta on 23.08.2016.
 */

@Singleton
@Component(modules = {ApplicationModule.class})

public interface IApplicationComponent {
    void inject(ActivationSmsPresenter activitionSmsPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(SignupPresenter signupPresenter);

    void inject(DatabaseInteractor databaseInteractor);

}
