package com.example.comer.autosmslogin.signup;

import java.util.ArrayList;

/**
 * Created by comer on 23.08.2016.
 */
public interface ISignupPresenter {
    void Databasecontrol();
    ArrayList<String>kayit();

    void clickSave(String username, String password);

}
