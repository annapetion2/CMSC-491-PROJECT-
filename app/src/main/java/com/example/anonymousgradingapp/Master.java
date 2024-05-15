package com.example.anonymousgradingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Todo;

import java.io.Serializable;
import java.util.ArrayList;
/*
GraphQL endpoint: https://h5pqnp4jebc5ff3yvcriuh6rji.appsync-api.us-east-1.amazonaws.com/graphql
GraphQL API KEY: da2-olwb7pwxwvdmfgepkuxto6iei4

Notes:
    After editing the Schema of the GraphQL API on the AWS Console website, remember to
    "amplify codegen models" in the command prompt of this project directory in order to update
    the schema locally.
 */
public class Master extends AppCompatActivity implements View.OnClickListener{
    private Button course_button;
    private Button exam_button;
    private Button barcode_button;
    private Button scan_button;
    private Button logout_button;
    private Button classList;
    private Button grade_scanner;
    public ArrayList<Course> courseList = new ArrayList<Course>(); //instantiate list of courses
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        course_button = (Button)findViewById(R.id.course_button);
        exam_button = (Button)findViewById(R.id.exam_button);
        barcode_button = (Button)findViewById(R.id.barcode_button);
        scan_button = (Button)findViewById(R.id.scan_button);
        logout_button = (Button)findViewById(R.id.logout);
        classList = (Button) findViewById(R.id.class_list);
        grade_scanner = (Button) findViewById(R.id.grade_scanner);

        //create a bundle and populated with the courseList in order to pass to other activities
        Bundle bundle = new Bundle();
        bundle.putSerializable("LIST",(Serializable)courseList);

        logout_button.setOnClickListener(this);

        course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCourseIntent = new Intent(Master.this, MainActivity.class);
                addCourseIntent.putExtras(bundle);
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
        grade_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanExamsIntent = new Intent(Master.this, textScanner.class);
                scanResult.launch(scanExamsIntent);
            }
        });
        classList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent classListIntent = new Intent(Master.this, CourseListDisplay.class);
                scanResult.launch(classListIntent);
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

    ActivityResultLauncher<Intent> classResult =
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