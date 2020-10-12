package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_s")
    private Long id; //!!!!!!!!! id_s

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "teacher_id")
    private  Long teacherId;
}
