package com.example.comer.autosmslogin.login;

import com.example.comer.autosmslogin.models.User;

/**
 * Created by musta on 23.08.2016.
 */
public interface IDatabaseInteractor {
    void addUser(User user);

    User checkUser(String username);

    boolean checkEMail(String username);

    void setEmailTrue(String username);
}
