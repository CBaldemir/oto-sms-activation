package com.example.comer.autosmslogin.activation;

/**
 * Created by comer on 23.08.2016.
 */
public interface IActivationSmsView {
    void incomingMessage(String msg);
    void saveSucces();
    void wrongMsg();
    void permissionSuccess();
    void permissionFailed();
}
