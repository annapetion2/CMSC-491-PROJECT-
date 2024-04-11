package com.example.anonymousgradingapp;

public class Exam{
    protected String name; //exam name
    public Exam(){}
    public Exam(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
