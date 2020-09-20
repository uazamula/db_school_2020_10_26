package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_s;

    @Column
    private String subject_name;

    @Column
    private Long class_id;

    @Column
    private  Long teacher_id;
}
