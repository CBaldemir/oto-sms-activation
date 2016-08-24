package com.example.comer.autosmslogin.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by musta on 23.08.2016.
 */
public class User extends RealmObject {


    @PrimaryKey
    private String username;
    private String password;
    private boolean eMail = false;

    public User() {

    }

    public boolean getEMail() {
        return eMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void seteMail(boolean eMail) {
        this.eMail = eMail;
    }
}
