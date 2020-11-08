package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Table(name = "grades")
public class Grade implements Comparable<Grade>{
   @Column(name = "pupil")
    private Long pupil;
   @Column(name = "subject")
    private Long subject;
   @Column(name = "grade")
    private Long gradePupil;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Override
    public int compareTo(Grade other) {
       return  this.getId().compareTo(other.getId());
    }

    public Long getPupil() {
        return pupil;
    }

    public void setPupil(Long pupil) {
        this.pupil = pupil;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public Long getGradePupil() {
        return gradePupil;
    }

    public void setGradePupil(Long gradePupil) {
        this.gradePupil = gradePupil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
