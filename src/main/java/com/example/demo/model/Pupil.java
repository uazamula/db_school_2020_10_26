package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="pupils")
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_p;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "class_p")
    private Long class_p;
}
