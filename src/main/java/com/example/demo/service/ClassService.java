package com.example.demo.service;

import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    private  final ClassRepository classRepository;

    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }
    public Class findById(Long id){
        return classRepository.getOne(id);
    }
    public List<Class> findAll(){
        return classRepository.findAll();
    }
    public Class saveClass(Class aClass){
        return classRepository.save(aClass);
    }
    public void deleteById(Long id){
        classRepository.deleteById(id);
    }
}
