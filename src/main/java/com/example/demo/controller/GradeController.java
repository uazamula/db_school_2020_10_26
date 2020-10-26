package com.example.demo.controller;

import com.example.demo.model.Grade;
import com.example.demo.model.Pupil;
import com.example.demo.model.Subject;
import com.example.demo.service.GradeService;
import com.example.demo.service.PupilService;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GradeController {
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final PupilService pupilService;
    private Subject e;

    @Autowired
    public GradeController(GradeService gradeService, SubjectService subjectService, PupilService pupilService) {
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.pupilService = pupilService;
    }
    @GetMapping("/grades")
    public String findAll(Model model)
    {
        List<Grade> grades = gradeService.findAll();
        model.addAttribute("grades", grades);
        for(Grade e:grades)
            System.out.println(e.toString());
        return "grade-list";
    }

    @GetMapping("/grade-create")
    public String createGradeFormNew(Grade grade, Model model, Subject subject, Pupil pupil){
        List<Subject> subjects = subjectService.findAll();////////////////////////////////////////////
        model.addAttribute("subjects", subjects);////////////////////////////////////
        List<Pupil> pupils = pupilService.findAll();////////////////////////////////////////////
        model.addAttribute("pupils", pupils);////////////////////////////////////
        for(Subject e:subjects)
            System.out.println(e.toString());

        for(Pupil e:pupils)
            System.out.println(e.toString());

        return "/grade-create";
    }

    @PostMapping("/grade-create")
    public String createGrade(Grade grade, Model model, Subject subject){
        List<Subject> subjects = subjectService.findAll();////////////////////////////////////////////
        model.addAttribute("subjects", subjects);////////////////////////////////////
        for(Subject e:subjects)
            System.out.println(e.toString());
        System.out.println(grade.toString());
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("grade-delete/{id}")
    public String deleteGrade(@PathVariable("id") Long id){
        gradeService.deleteById(id);
        return "redirect:/grades";
    }

    @GetMapping("grade-update/{id}")
    public String updateGradeForm(@PathVariable("id") Long id, Model model){
        Grade grade = gradeService.findById(id);
        model.addAttribute("grade",grade);// "grade" hier != "grade" field in model
        return "grade-update";
    }
    @PostMapping("/grade-update")
    public String updateGrade(Grade grade){
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }
}
