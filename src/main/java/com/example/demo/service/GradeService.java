package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.model.Subject;
import com.example.demo.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade findById(Long id){return gradeRepository.getOne(id);}
    public List<Grade> findAll(){return gradeRepository.findAll();}
    public Grade saveGrade(Grade grade){return gradeRepository.save(grade);}
    public void deleteById(Long id){ gradeRepository.deleteById(id);}


    //public List<Subject> findAllSubjectName(){return gradeRepository.findAllSubjectName();}///////////////////
}
