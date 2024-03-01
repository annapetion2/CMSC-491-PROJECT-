package com.example.anonymousgradingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Master extends AppCompatActivity implements View.OnClickListener{
    private Button course_button;
    private Button exam_button;
    private Button barcode_button;
    private Button scan_button;
    private Button logout_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        course_button = (Button)findViewById(R.id.course_button);
        exam_button = (Button)findViewById(R.id.exam_button);
        barcode_button = (Button)findViewById(R.id.barcode_button);
        scan_button = (Button)findViewById(R.id.scan_button);
        logout_button = (Button)findViewById(R.id.logout);

        logout_button.setOnClickListener(this);

        course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCourseIntent = new Intent(Master.this, MainActivity.class);
                courseResult.launch(addCourseIntent);
            }
        });

        exam_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addExamIntent = new Intent(Master.this, MainActivity2.class);
                examResult.launch(addExamIntent);
            }
        });
        barcode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showBarcodesIntent = new Intent(Master.this, BarcodeName.class);
                barcodeResult.launch(showBarcodesIntent);
            }
        });
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanExamsIntent = new Intent(Master.this, MainActivity4.class);
                scanResult.launch(scanExamsIntent);
            }
        });
    }

    ActivityResultLauncher<Intent> courseResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                //do something
                            }
                        }
                    });
    ActivityResultLauncher<Intent> examResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                //do something
                            }
                        }
                    });
    ActivityResultLauncher<Intent> scanResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                //do something
                            }
                        }
                    });
    ActivityResultLauncher<Intent> barcodeResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                //do something
                            }
                        }
                    });

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.logout){
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }
}