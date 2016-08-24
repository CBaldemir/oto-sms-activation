package com.example.comer.autosmslogin.signup;

/**
 * Created by comer on 23.08.2016.
 */
public interface ISignupView {
    void onSuccess();
    void onFailed();
    public void goActivation(String username);
    void clickSave();

}
