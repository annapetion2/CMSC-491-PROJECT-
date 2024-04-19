package com.example.anonymousgradingapp;

import java.util.Dictionary;
import java.util.Hashtable;

public class Exam{
    protected String name; //exam name
    public Dictionary<String, Integer> grades = new Hashtable<>();
    public Exam(){}
    public Exam(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return name;
    }


}
