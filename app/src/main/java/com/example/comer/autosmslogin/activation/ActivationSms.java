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
import com.example.comer.autosmslogin.login.Login;
import com.example.comer.autosmslogin.services.SmsService;

import java.util.ArrayList;
import java.util.List;

public class ActivationSms extends AppCompatActivity implements IActivationSmsView {
public int REQUEST_PERMISSIONS=0;
    List<String> izinler = new ArrayList<String>();
    Bundle bundle;
    String username, msg;
    Intent intent;
    private IActivationSmsPresenter activationSmsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activition__sms);



        int smsOkumaIzni = ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.RECEIVE_SMS);
        if(smsOkumaIzni != PackageManager.PERMISSION_GRANTED){
            izinler.add(Manifest.permission.RECEIVE_SMS);
        }
        if(!izinler.isEmpty()){
            ActivityCompat.requestPermissions(this,izinler.toArray(new String[izinler.size()]), REQUEST_PERMISSIONS);}
    }

    @Override
    protected void onNewIntent(Intent intent) {
        serviceControl();
        bundle = intent.getExtras();
        try {
            msg = bundle.getString("msg");
        } catch (Exception e) {
        }
        username = SmsService.username;
        if (username != null && msg != null) {
            incomingMessage(msg);
        }
            super.onNewIntent(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch ( requestCode ) {
            case 0: {
                for( int i = 0; i < permissions.length; i++ ) {
                    if( grantResults[i] == PackageManager.PERMISSION_GRANTED ) {
                        Toast.makeText(ActivationSms.this, "İzin başarıyla verildi", Toast.LENGTH_SHORT).show();
                    } else if( grantResults[i] == PackageManager.PERMISSION_DENIED ) {

                        Toast.makeText(ActivationSms.this, "Eğer izin vermezseniz aktivasyon yapamazsınız", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            default: {
                onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

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

    @Override
    public void permissionSuccess() {
        Toast.makeText(ActivationSms.this, "İzin başarılı", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void permissionFailed() {
        Toast.makeText(ActivationSms.this, "Eğer izin vermezseniz sms activasyonunu yapamazsınız", Toast.LENGTH_SHORT).show();
    }

    private void serviceControl() {
        activationSmsPresenter = new ActivationSmsPresenter(this, getApplication());
     }}