package com.example.anonymousgradingapp;

import android.graphics.Bitmap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.opencsv.CSVReader;

//This class serves as a general object for every class that the user decides to name and create in
//the anonymous grading application
public class Course {
    protected String name; //course name
    protected String instructor; //instructor name
    protected CSVReader roster; //full roster of students
    protected ArrayList<String> Exams; //list of all exams
    protected ArrayList<ArrayList<Bitmap>> barcodes; //for each exam, barcodes for each student
    protected ArrayList<Student> studentList; //for each exam, grades/bar

    public Course(){}
    public Course(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getInstructor(){
        return instructor;
    }

}
