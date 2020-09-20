package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @GetMapping("/subjects")
    public String findAll(Model model){
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);
        return "subject-list";
    }
    @GetMapping("/subject-create")
    public String createSubjectForm(Subject subject){
        return "/subject-create";
    }

    @PostMapping("/subject-create")
    public String createSubject(Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("subject-delete/{id_s}")
    public String deleteSubject(@PathVariable("id_s") Long id){
        subjectService.deleteById(id);
        return "redirect:/subjects";
    }

    @GetMapping("/subject-update/{id_s}")
    public String updateSubjectForm(@PathVariable("id_s") Long id, Model model){
        Subject subject = subjectService.findById(id);
        model.addAttribute("subject", subject);
        return "subject-update";
    }
    @PostMapping("/subject-update")
    public String updateSubject(Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }
}
