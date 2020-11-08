// https://stackoverflow.com/questions/13154818/how-to-define-a-jpa-repository-query-with-a-join
package com.example.demo.model;

import com.example.demo.controller.ClassNew;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="classes")
public class Class implements Comparable<Class>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_c")
    private Long id;
    @Column(name = "class_int")
    private int classInt;
    @Column(name = "class_char")
    private char classChar;
    @Column(name = "teacher_id")
    private Long teacherId;

    //private String teacherName;
//    public String getTeacherName() {return teacherName;}
//    public void setTeacherName(User user) {
//        if (this.getTeacherId()==user.getId())
//        this.teacherName = user.getLastName() + " " + user.getFirstName();
//    }

    @Override
    public int compareTo(Class other) {
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "aClass")
//    private User users;



   /* public Class() {
    }

    public Class(Long id_c, int class_int, char class_char, Long teacher_id) {
        this.id_c = id_c;
        this.class_int = class_int;
        this.class_char = class_char;
        this.teacher_id = teacher_id;
    }

    public Long getId_c() {
        return id_c;
    }

    public void setId_c(Long id_c) {
        this.id_c = id_c;
    }

    public int getClass_int() {
        return class_int;
    }

    public void setClass_int(int class_int) {
        this.class_int = class_int;
    }

    public char getClass_char() {
        return class_char;
    }

    public void setClass_char(char class_char) {
        this.class_char = class_char;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    */

    /*@OneToOne(fetch = FetchType.EAGER,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id"/*
    ,nullable = false,updatable = false,insertable = false)
    //public User getUser(){return user;}
    private User user;*/

}
