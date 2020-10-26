package com.example.demo.controller;

public class ClassNew {
    private Long id;
    private int classInt;
    private char classChar;
    private String teacher;

    public ClassNew(){}
    public ClassNew(Long id, int classInt, char classChar, String teacher) {
        this.id = id;
        this.classInt = classInt;
        this.classChar = classChar;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getClassInt() {
        return classInt;
    }

    public void setClassInt(int classInt) {
        this.classInt = classInt;
    }

    public char getClassChar() {
        return classChar;
    }

    public void setClassChar(char classChar) {
        this.classChar = classChar;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
