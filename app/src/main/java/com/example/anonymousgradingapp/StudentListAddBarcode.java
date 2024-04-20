package com.example.anonymousgradingapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentListAddBarcode extends AppCompatActivity {
    private ListView listview_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_add_barcode);

        listview_ = (ListView)findViewById(R.id.listviewview2);
        //listAdapter = new ArrayAdapter<Student>(this, R.layout.textviewforlistview,
               // R.id.holder, GlobalVariable.courseList.get(GlobalVariable.pos).studentList);
        int index = getIntent().getIntExtra("key",-1);
        if(index > -1) {
            StudentAdapter listAdapter = new StudentAdapter(getApplicationContext(),
                    GlobalVariable.courseList.get(GlobalVariable.pos).getStudents(),
                    GlobalVariable.courseList.get(GlobalVariable.pos).exams.get(index).getBarcodes());

            listview_.setAdapter(listAdapter);
        }
    }
}