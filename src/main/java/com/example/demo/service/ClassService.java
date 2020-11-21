package com.example.demo.service;

import com.example.demo.model.Class;
import com.example.demo.model.User;
import com.example.demo.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Class> findPaginated(int pageNo, int pageSize,
                                     String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();


        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.classRepository.findAll(pageable);
    }
}
