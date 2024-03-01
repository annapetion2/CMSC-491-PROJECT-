package com.example.anonymousgradingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anonymousgradingapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity{

    private TextInputEditText enterName;
    private TextInputEditText enterPass;
    private Button submit;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enterName = (TextInputEditText)findViewById(R.id.UserName);
        enterPass = (TextInputEditText)findViewById(R.id.Password);
        submit = (Button)findViewById(R.id.Submit);
        register = (Button)findViewById(R.id.Register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                registerResult.launch(registerIntent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Login.this, Master.class);
                registerResult.launch(loginIntent);
            }
        });
    }
    ActivityResultLauncher<Intent> registerResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                //do something
                            }
                        }
                    });
    ActivityResultLauncher<Intent> loginResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                //do something
                            }
                        }
                    });
}