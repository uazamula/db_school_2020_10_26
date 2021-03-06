package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="pupils")
public class Pupil implements Comparable<Pupil>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_p")
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "class_p")
    private Long classP;

    @Override
    public int compareTo(Pupil other) {
        int i = this.getLastName().compareTo(other.getLastName());
        if (i==0)
            return this.getFirstName().compareTo(other.getFirstName());
        else
            return i;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getClassP() {
        return classP;
    }

    public void setClassP(Long classP) {
        this.classP = classP;
    }
}
