package com.example.comer.autosmslogin.activation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.comer.autosmslogin.login.Login;
import com.example.comer.autosmslogin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ActivationSms extends AppCompatActivity implements IActivationSmsView {
    private static final int REQUEST_PERMISSIONS = 0;

    List<String> izinler = new ArrayList<String>();
    int bellekokumaizni;
    int smsOkumaIzni = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

    String username;
    public ActivationSms() {
        this.username=getIntent().getExtras().getString("username");
    }

    private IActivationSmsPresenter activationSmsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activition__sms);
        activationSmsPresenter = new ActivationSmsPresenter(this,getApplication(),username);
        ButterKnife.bind(this);

        if(bellekokumaizni != PackageManager.PERMISSION_GRANTED){
            izinler.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch ( requestCode ) {
            case REQUEST_PERMISSIONS: {
                for( int i = 0; i < permissions.length; i++ ) { // İstediğimiz izinler dolaşalım
                    if( grantResults[i] == PackageManager.PERMISSION_GRANTED ) { // Eğer izin verilmişse
                        Log.d( "Permissions", "İzin Verildi: " + permissions[i] ); // İsmiyle birlikte izin verildi yazıp log basalım.

// İzin verildiği için burada istediğiniz işlemleri yapabilirsiniz. Verilen izne göre sms okuyabilir ve belleğe yazabilirsiniz.
                    } else if( grantResults[i] == PackageManager.PERMISSION_DENIED ) { // Eğer izin reddedildiyse
                        Log.d( "Permissions", "İzin Reddedildi: " + permissions[i] ); // İsmiyle birlikte reddedildi yazıp log basalım.
// Burada bir toast mesajı gösterebilirsiniz. Mesela bu işlemi yapabilmek için izin vermeniz gereklidir. gibi..
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
    @Override
    public void Actitivationsms() {

    }

    @Override
    public void incomingMessage(String msg) {
        activationSmsPresenter.incomingMessage(msg);
    }

    @Override
    public void saveSucces() {
        finish();
        Intent loginol = new Intent(getApplication(), Login.class);
        startActivity(loginol);
    }

}