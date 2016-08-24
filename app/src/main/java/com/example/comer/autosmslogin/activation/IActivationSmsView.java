package com.example.comer.autosmslogin.activation;

/**
 * Created by comer on 23.08.2016.
 */
public interface IActivationSmsView {
    void Actitivationsms();
    void incomingMessage(String msg);
    void saveSucces();
}
