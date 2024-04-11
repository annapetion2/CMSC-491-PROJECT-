package com.example.anonymousgradingapp;

import android.graphics.Bitmap;

import com.opencsv.CSVReader;

import java.util.ArrayList;

//This class serves as a general object for every class that the user decides to name and create in
//the anonymous grading application
public class Course {
    protected String name; //course name
    protected String instructor; //instructor name
    protected CSVReader roster; //full roster of students
    protected ArrayList<Exam> exams = new ArrayList<Exam>(); //list of all exams
    protected ArrayList<ArrayList<Bitmap>> barcodes; //for each exam, barcodes for each student
    protected ArrayList<Student> studentList; //for each exam, grades/bar

    public Course(){}
    public Course(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getInstructor(){return instructor;};

    //According to android API, ArrayAdapter by default displays the value returned by the custom
    //object's toString() method, therefore to dispaly the names of the courses on the listView
    //using an ArrayAdapter, this class must override the toString() method to return the string
    //of the object's name.
    @Override
    public String toString() {
        return name;
    }

}
