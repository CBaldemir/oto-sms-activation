package com.example.comer.autosmslogin.activation;

/**
 * Created by comer on 23.08.2016.
 */
public interface IActivationSmsPresenter {
    void ActivationSmsSubmit();
    void incomingMessage(String msg);
}
