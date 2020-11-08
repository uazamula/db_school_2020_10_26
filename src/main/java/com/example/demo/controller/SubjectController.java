package com.example.demo.controller;

import com.example.demo.model.Class;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.ClassService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SubjectController {
    private final SubjectService subjectService;
    private final ClassService classService;
    private final UserService userService;
    @Autowired
    public SubjectController(SubjectService subjectService, ClassService classService, UserService userService) {
        this.subjectService = subjectService;
        this.classService = classService;
        this.userService = userService;
    }
    @GetMapping("/subjects")
    public String findAll(Model model){
        List<Subject> subjects = subjectService.findAll();
        Collections.sort(subjects);
        model.addAttribute("subjects", subjects);
        List<Class> classes = classService.findAll();/////////////////////////
        model.addAttribute("classes", classes);////////////////
        List<User> users = userService.findAll();////////////////////////////////////////////
        model.addAttribute("teachers", users);////////////////////////////////////
        return "subject-list";
    }
    @GetMapping("/subject-create")
    public String createSubjectForm(Subject subject, Model model){
        List<Class> classes = classService.findAll();/////////////////////////
        Collections.sort(classes);
        model.addAttribute("classes", classes);////////////////
        List<User> users = userService.findAll();////////////////////////////////////////////
        Collections.sort(users);
        model.addAttribute("teachers", users);////////////////////////////////////


        return "/subject-create";
    }

    @PostMapping("/subject-create")
    public String createSubject(Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("subject-delete/{id}")//!!!!!! id_s
    public String deleteSubject(@PathVariable("id") Long id){//!!!!!!!!! id_s
        subjectService.deleteById(id);
        return "redirect:/subjects";
    }

    @GetMapping("/subject-update/{id}")//!!!!!!! id_s
    public String updateSubjectForm(@PathVariable("id") Long id, Model model){//!!!!!!! id_s
        Subject subject = subjectService.findById(id);
        model.addAttribute("subject", subject);
        List<Class> classes = classService.findAll();/////////////////////////
        Collections.sort(classes);
        model.addAttribute("classes", classes);////////////////
        List<User> users = userService.findAll();////////////////////////////////////////////
        Collections.sort(users);
        model.addAttribute("teachers", users);////////////////////////////////////
        return "subject-update";
    }
    @PostMapping("/subject-update")
    public String updateSubject(Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }
}
