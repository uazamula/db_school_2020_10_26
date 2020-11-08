package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.model.User;

import javax.persistence.Column;

public class SubjectNew implements Comparable<SubjectNew>{
    private Long id; //!!!!!!!!! id_s
    private String subjectName;
    private Long classId;
    private String className;
    private  Long teacherId;
    private String teacherName;

    public SubjectNew(){}
    public SubjectNew(Long id, String subjectName, Long classId, String className, Long teacherId, String teacherName) {
        this.id = id;
        this.subjectName = subjectName;
        this.classId = classId;
        this.className = className;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
    @Override
    public int compareTo(SubjectNew other) {
        int i = this.getClassName().compareTo(other.getClassName());
        if (i==0) return this.getSubjectName().compareTo(other.getSubjectName());
        else
            return i;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
