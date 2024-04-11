package com.example.anonymousgradingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseListDisplay extends AppCompatActivity implements View.OnClickListener{

    private ListView listview_;
    private ArrayAdapter<Course> listAdapter;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_display);

        back = (Button)findViewById(R.id.back_to_master5);
        listview_ = (ListView)findViewById(R.id.listDisplay);
        listAdapter = new ArrayAdapter<Course>(this, R.layout.textviewforlistview,
                R.id.holder, GlobalVariable.courseList);

        listview_.setAdapter(listAdapter);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back_to_master5){
            Intent emptyIntent = new Intent();  //make new empty intent
            setResult(RESULT_OK, emptyIntent); //set intent to RESULT_OK
            finish();   //finish this screen and return to master
        }
    }
}