package com.example.demo.controller;

public class ClassNew implements Comparable<ClassNew>{
    private Long id;
    private int classInt;
    private char classChar;
    private String teacher;

    @Override
    public int compareTo(ClassNew other) {
        int i=0;
        if(classInt < other.classInt)
            i=-1;
        else
            if (classInt > other.classInt)
                i = 1;
            else
                if (classChar < other.classChar)
                    i = -1;
        return i;
    }

    /*
    @Override    // This method doesn't work properly, e.g.: 10a, 11a, 11b, 2a, 4a, 6a
    public int compareTo(ClassNew other) {
        String str =       classInt + "" +       classChar,
          otherStr = other.classInt + "" + other.classChar;
        return str.compareTo(otherStr);
    }
     */

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
