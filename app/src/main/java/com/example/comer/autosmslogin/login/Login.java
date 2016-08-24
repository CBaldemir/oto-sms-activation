package com.example.comer.autosmslogin.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comer.autosmslogin.R;
import com.example.comer.autosmslogin.activation.ActivationSms;
import com.example.comer.autosmslogin.signup.SignUp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity implements ILoginView{

    @BindView(R.id.signup)
    TextView login;
    @BindView(R.id.kullanici)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    private ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, getApplication());
    }

    @OnClick({R.id.signup, R.id.button})
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            loginPresenter.clickLogIn(userName.getText().toString(), password.getText().toString());
        }
        if(view.getId()==R.id.signup)
        {
            loginPresenter.clickSignUp();
        }

    }


    @Override
    public void goActivation(String username) {
        Intent loginol = new Intent(Login.this, ActivationSms.class);
        loginol.putExtra("username", username);
        startActivity(loginol);
    }

    @Override
    public void signUp() {
        Intent loginol = new Intent(getApplication(), SignUp.class);
        startActivity(loginol);
    }

    @Override
    public void setEMailTrue(String username) {
        Intent loginol = new Intent();
        loginol.putExtra("username", username);
        loginol.setClass(getApplicationContext(), ActivationSms.class);
        startActivity(loginol);
        Toast.makeText(Login.this, "Lütfen hesap aktivasyonunu yapınız", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void accesError() {
        Toast.makeText(Login.this, "Kullanıcı adı veya şifre hatalı,eğer kayıtlı " +
                "değilseniz lütfen kayıt olunuz", Toast.LENGTH_LONG).show();
    }

    @Override
    public void fiilAllError() {
        Toast.makeText(Login.this, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void trueAcces() {
        Toast.makeText(Login.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
    }

}