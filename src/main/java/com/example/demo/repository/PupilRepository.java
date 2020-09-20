package com.example.demo.repository;

import com.example.demo.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PupilRepository extends JpaRepository<Pupil,Long> {
}
