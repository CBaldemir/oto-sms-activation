package com.example.comer.autosmslogin.login;

/**
 * Created by comer on 23.08.2016.
 */
public interface ILoginView {
    void onSuccess();
    void onFailed();
    void onActive();
    void goActivation(String username);
    void signUp();

    void toast(String text);





}
