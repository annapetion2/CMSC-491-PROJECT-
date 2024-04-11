package com.example.anonymousgradingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseListAddExam extends AppCompatActivity{

    private ListView listview_;
    private ArrayAdapter<Course> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list_add_exam);

        listview_ = (ListView)findViewById(R.id.listviewview);
        listAdapter = new ArrayAdapter<Course>(this, R.layout.textviewforlistview,
                R.id.holder, GlobalVariable.courseList);

        listview_.setAdapter(listAdapter);

        listview_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //view is the object for the entire item
                //position is the item being click on

                //Course course = GlobalVariable.courseList.get(position);

                Intent courseIntent = new Intent();
                courseIntent.putExtra("Key", position); //return position of selected course
                setResult(RESULT_OK, courseIntent);
                finish();
            }
        });
    }
}