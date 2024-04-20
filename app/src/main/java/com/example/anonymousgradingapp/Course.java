package com.example.anonymousgradingapp;

import android.graphics.Bitmap;

import java.util.ArrayList;

//This class serves as a general object for every class that the user decides to name and create in
//the anonymous grading application
public class Course {
    protected String name; //course name
    protected String instructor; //instructor name
    protected ArrayList<Student> studentList = new ArrayList<Student>(); //for each exam, grades/bar
    protected String[] students = new String[1000];
    protected Bitmap[] barcodes2 = new Bitmap[1000];
    protected ArrayList<Exam> exams = new ArrayList<Exam>(); //list of all exams
    //for each exam, barcodes for each student, index 0 = exam 1, index 1 = exam 2, index 2 = exam 3, etc
    protected ArrayList<ArrayList<Bitmap>> barcodes = new ArrayList<ArrayList<Bitmap>>();
    public Course(){}
    public Course(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public String getInstructor(){return instructor;};

    //According to android API, ArrayAdapter by default displays the value returned by the custom
    //object's toString() method, therefore to display the names of the courses on the listView
    //using an ArrayAdapter, this class must override the toString() method to return the string
    //of the object's name.
    @Override
    public String toString() {
        return name;
    }

    public String[] getStudents(){
        for(int i = 0; i < this.studentList.size(); i++){
            String info =   this.studentList.get(i).fname + " " +
                    this.studentList.get(i).lname + " " +
                    this.studentList.get(i).ID;
            students[i] = info;
        }
        GlobalVariable.ssize = studentList.size();
        return students;
    }

    public Bitmap[] getBarcodes(){
        for(int i = 0; i < GlobalVariable.courseList.get(GlobalVariable.pos).studentList.size(); i++) {
            barcodes2[i] = GlobalVariable.courseList.get(GlobalVariable.pos).barcodes.
                    get(GlobalVariable.epos).get(i);
        }
        return barcodes2;
    }

}
