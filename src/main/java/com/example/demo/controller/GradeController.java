package com.example.demo.controller;

import com.example.demo.model.Grade;
import com.example.demo.service.GradeService;
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

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @GetMapping("/grades")
    public String findAll(Model model){
        List<Grade> grades = gradeService.findAll();
        model.addAttribute("grades", grades);
        return "grade-list";
    }
    @GetMapping("/grade-create")
    public String createGradeForm(Grade grade){
        return "/grade-create";
    }

    @PostMapping("/grade-create")
    public String createGrade(Grade grade){
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
        model.addAttribute("grade",grade);
        return "grade-update";
    }
    @PostMapping("/grade-update")
    public String updateGrade(Grade grade){
        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }
}
