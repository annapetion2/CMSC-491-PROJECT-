package com.example.anonymousgradingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    private Button examButton;
    private Button back_button;
    private EditText examName;
    private TextView displayExam;
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
                displayExam.setText("Added exam: " + exam_name);
                //display ListView of instanced courses and add the exam name to the arraylist Exams
            }
        }else if(v.getId() == R.id.back_to_master2){
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }
}