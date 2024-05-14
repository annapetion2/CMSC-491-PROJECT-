package com.example.anonymousgradingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button register, confirmbutton, back;
    private TextInputEditText email;
    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputEditText confirmcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (TextInputEditText)findViewById(R.id.Email);
        username = (TextInputEditText)findViewById(R.id.UserName);
        password = (TextInputEditText)findViewById(R.id.Password);
        confirmcode = (TextInputEditText)findViewById(R.id.confirmcode);

        register = (Button)findViewById(R.id.Submit);
        confirmbutton = (Button)findViewById(R.id.rconfirm);
        back = (Button)findViewById(R.id.back_to_login2);

        confirmbutton.setOnClickListener(this);
        register.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Submit) {
            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), email.getText().toString())
                    .build();
            Amplify.Auth.signUp(
                    email.getText().toString(),
                    password.getText().toString(),
                    options,
                    result -> Log.i("Amplify Auth", "Result: " + result.toString()),
                    error -> Log.e("Amplify Auth", "Sign up failed", error)
            );
            confirmbutton.setVisibility(View.VISIBLE);
        }else if(v.getId() == R.id.rconfirm){
            Amplify.Auth.confirmSignUp(email.getText().toString(),
                    confirmcode.getText().toString(),
                    result -> Log.d("AmplifyRegister", "Result: " + result.toString()),
                    error -> Log.e("AmplifyRegister", "Error: " + error.toString())); //lambda notation

        }else if(v.getId() == R.id.back_to_login2){
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }
}