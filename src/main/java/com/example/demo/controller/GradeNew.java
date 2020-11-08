package com.example.demo.controller;

import com.example.demo.model.Grade;

public class GradeNew implements Comparable<GradeNew>{
    private Long id;
    private Long classId;
    private int classInt;
    private char classChar;
    private Long pupilId;
    private String pupilFirstName;
    private String pupilLastName;
    private Long subjectId;
    private String subjectName;
    private Long gradePupil;
    @Override
    public int compareTo(GradeNew other) {
        int i=0;
        if(classInt < other.classInt)
            i=-1;
        else
        if (classInt > other.classInt)
            i = 1;
        else
            if (classChar < other.classChar)
                i = -1;
            else
            if (classChar > other.classChar)
                i = 1;
            else
                i = (pupilLastName + " " +pupilFirstName).compareTo(
                        other.pupilLastName + " " + other.pupilFirstName);
        return i;
    }

    public GradeNew(){};
    public GradeNew(Long id,
                    Long classId, int classInt, char classChar,
                    Long pupilId, String pupilFirstName, String pupilLastName,
                    Long subjectId, String subjectName,
                    Long gradePupil) {
        this.id = id;
        this.classId = classId;
        this.classInt = classInt;
        this.classChar = classChar;
        this.pupilId = pupilId;
        this.pupilFirstName = pupilFirstName;
        this.pupilLastName = pupilLastName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.gradePupil = gradePupil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
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

    public Long getPupilId() {
        return pupilId;
    }

    public void setPupilId(Long pupilId) {
        this.pupilId = pupilId;
    }

    public String getPupilFirstName() {
        return pupilFirstName;
    }

    public void setPupilFirstName(String pupilFirstName) {
        this.pupilFirstName = pupilFirstName;
    }

    public String getPupilLastName() {
        return pupilLastName;
    }

    public void setPupilLastName(String pupilLastName) {
        this.pupilLastName = pupilLastName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getGradePupil() {
        return gradePupil;
    }

    public void setGradePupil(Long gradePupil) {
        this.gradePupil = gradePupil;
    }
}
