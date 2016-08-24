package com.example.comer.autosmslogin.activation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.comer.autosmslogin.R;
import com.example.comer.autosmslogin.SmsApp;
import com.example.comer.autosmslogin.login.Login;

import java.util.ArrayList;
import java.util.List;

public class ActivationSms extends AppCompatActivity implements IActivationSmsView {
public int REQUEST_PERMISSIONS=0;
    List<String> izinler = new ArrayList<String>();
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
        int smsOkumaIzni = ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.RECEIVE_SMS);
        if(smsOkumaIzni != PackageManager.PERMISSION_GRANTED){
            izinler.add(Manifest.permission.RECEIVE_SMS);
        }
        if(!izinler.isEmpty()){
            ActivityCompat.requestPermissions(this,izinler.toArray(new String[izinler.size()]), REQUEST_PERMISSIONS);}
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
            }}}