package com.example.anonymousgradingapp;

import java.util.ArrayList;

public class GlobalVariable {
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static int pos;
    public static int epos;
    public static int ssize;
    public static ArrayList<Integer> scanned_grade = new ArrayList<Integer>();
    public static ArrayList<String> scanned_ID = new ArrayList<String>();
    public static ArrayList<Integer> scanned_course = new ArrayList<Integer>();
    public static ArrayList<Integer> scanned_exam = new ArrayList<Integer>();
    private int student_pos = 0;

    //if there is a value in all 4 scanned course/exam/grade/ID, then grade the scanned student for the
    //scanned exam for the scanned course with the scanned grade
    public void grade(int size){

        for(int i = 0; i < size; i++) {
            if (scanned_ID.get(i) != null) {
                for (int j = 0; j < this.courseList.get(scanned_course.get(0)).studentList.size(); j++) {
                    if (this.courseList.get(pos).studentList.get(j).ID == scanned_ID.get(i)) {
                        student_pos = j;
                    }
                }
            }
            if(this.scanned_course.get(i) != null
                && this.scanned_ID.get(i) != null
                && this.scanned_grade != null
                && this.scanned_exam != null){
                this.courseList.get(scanned_course.get(i)).exams.get(scanned_exam.get(i)).grades
                        .put(this.courseList.get(scanned_course.get(i)).studentList.get(student_pos).ID
                                , scanned_grade.get(i));
            }
        }
    }

}
