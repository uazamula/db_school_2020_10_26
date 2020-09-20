package com.example.demo.service;

import com.example.demo.model.Pupil;
import com.example.demo.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilService {

    private final PupilRepository pupilRepository;

    @Autowired
    public PupilService(PupilRepository pupilRepository) {
        this.pupilRepository = pupilRepository;
    }

    public Pupil findById(Long id){return pupilRepository.getOne(id);}
    public List<Pupil> findAll(){return pupilRepository.findAll();}
    public Pupil savePupil(Pupil pupil){return pupilRepository.save(pupil);}
    public void deleteById(Long id){ pupilRepository.deleteById(id);}

}
