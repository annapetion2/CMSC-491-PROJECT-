package com.example.anonymousgradingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
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

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

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
                int success;
                Intent loginIntent = new Intent(Login.this, Master.class);
                Amplify.Auth.signIn(
                        enterName.getText().toString(),
                        enterPass.getText().toString(),
                        result -> Log.i("Amplify Auth", result.isSignedIn() ? "Sign in succeeded" : "Sign in not complete"),
                        error -> Log.e("Amplify Auth", error.toString())
                );
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