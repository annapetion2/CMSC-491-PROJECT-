package com.example.anonymousgradingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ExamListDisplay extends AppCompatActivity {
    private ListView listview_;
    private ArrayAdapter<Exam> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list_display);

        listview_ = (ListView)findViewById(R.id.listviewview3);
        listAdapter = new ArrayAdapter<Exam>(this, R.layout.textviewforlistview,
                R.id.holder, GlobalVariable.courseList.get(GlobalVariable.pos).exams);
        listview_.setAdapter(listAdapter);
        listview_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent courseIntent = new Intent();
                courseIntent.putExtra("Key", position); //return position of selected course
                setResult(RESULT_OK, courseIntent);
                finish();
            }
        });
    }

}