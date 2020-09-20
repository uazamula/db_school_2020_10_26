package com.example.demo.model;

import javax.persistence.*;


@Entity
@Table(name="classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_c;
    @Column(name = "class_int")
    private int class_int;
    @Column(name = "class_char")
    private char class_char;
    @Column(name = "teacher_id")
    private Long teacher_id;

    public Class() {
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
    /*@OneToOne(fetch = FetchType.EAGER,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id"/*
    ,nullable = false,updatable = false,insertable = false)
    //public User getUser(){return user;}
    private User user;*/

}
