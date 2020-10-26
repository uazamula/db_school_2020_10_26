package com.example.demo.repository;

import com.example.demo.model.Grade;
import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    //List<Subject> findAllSubjectName();///////////////////////////////////////////////////////////////
}
