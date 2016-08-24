package com.example.comer.autosmslogin.activation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.comer.autosmslogin.R;
import com.example.comer.autosmslogin.SmsApp;
import com.example.comer.autosmslogin.login.Login;

public class ActivationSms extends AppCompatActivity implements IActivationSmsView {


    Bundle bundle;
    String username, msg;
    Intent intent;
    boolean control = false;
    private IActivationSmsPresenter activationSmsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activition__sms);

        serviceControl();

    }


    @Override
    public void incomingMessage(String msg) {


        activationSmsPresenter.incomingMessage(msg, username);
    }

    @Override
    public void saveSucces() {
        finish();
        Intent loginol = new Intent(getApplication(), Login.class);
        startActivity(loginol);
    }

    @Override
    public void wrongMsg() {
        Toast.makeText(ActivationSms.this, "Yanlış mesaj", Toast.LENGTH_SHORT).show();
    }

    private void serviceControl() {
        activationSmsPresenter = new ActivationSmsPresenter(this, getApplication());
        intent = getIntent();
        bundle = intent.getExtras();
        username = bundle.getString("username");
        msg = bundle.getString("msg");
        {
            if (username == null) {
                username = SmsApp.username;
                control = true;
            } else {
                SmsApp.username = username;
                control = false;
            }
        }
        username = username + "";
        if (control)
            if (username != null && msg != null) {
                incomingMessage(msg);
                control = false;
            }
    }




}