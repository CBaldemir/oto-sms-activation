package com.example.comer.autosmslogin.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comer.autosmslogin.R;
import com.example.comer.autosmslogin.activation.ActivationSms;
import com.example.comer.autosmslogin.services.SmsService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity implements ISignupView {
    @BindView(R.id.nameis)
    EditText username;
    @BindView(R.id.sifre)
    EditText password;
    @BindView(R.id.sing_up_button)
    Button signUp;
private ISignupPresenter signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        signupPresenter = new SignupPresenter(this, getApplication());
        ButterKnife.bind(this);

    }

    @OnClick(R.id.sing_up_button)
    public void onClick(View view) {
        signupPresenter.clickSave(username.getText().toString(), password.getText().toString());

    }
    @Override
    public void onSuccess() {
        Toast.makeText(SignUp.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goActivation(String username) {
        Intent loginol = new Intent();
        loginol.setClass(getApplicationContext(), ActivationSms.class);
        SmsService.username = username;
        startActivity(loginol);
    }

    @Override
    public void emptyAllert() {
        Toast.makeText(SignUp.this, "Lütfen gerekli alanları doldurunuz.", Toast.LENGTH_SHORT).show();
    }
}
