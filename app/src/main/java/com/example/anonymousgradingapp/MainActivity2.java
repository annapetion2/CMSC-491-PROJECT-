package com.example.anonymousgradingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    private Button examButton;
    private Button back_button;
    private EditText examName;
    private TextView displayExam;
    private ListView listview_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //initialize button and textview
        examButton = (Button) findViewById(R.id.examButton);
        back_button = (Button) findViewById(R.id.back_to_master2);
        displayExam = (TextView) findViewById(R.id.displayExam);
        examName = (EditText) findViewById(R.id.examName);

        examButton.setOnClickListener(this);
        back_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.examButton){
            String exam_name = examName.getText().toString();
            if(exam_name != ""){
                Intent myIntent = new Intent(MainActivity2.this,CourseListAddExam.class);
                //display ListView of instanced courses and add the exam name to the arraylist Exams
                listResult.launch(myIntent);
            }
        }else if(v.getId() == R.id.back_to_master2){
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }
    ActivityResultLauncher<Intent> listResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        /*
                        //get the index of the course the user selected to add an exam to
                        int index = o.getData().getIntExtra("Key",0);
                        //create a new exam with the inputted name
                        Exam newExam = new Exam(examName.getText().toString());
                        //access the course the user selected, then append the new exam instance to
                        //the course's examList
                        GlobalVariable.courseList.get(index).exams.add(newExam);
                        displayExam.setText("Added exam: " + examName.getText().toString() + " to " +
                                GlobalVariable.courseList.get(index).getName());
                         */

                    }
                }
            });
}