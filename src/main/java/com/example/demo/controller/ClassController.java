package com.example.demo.controller;

import com.example.demo.model.Class;
import com.example.demo.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }
    @GetMapping("/classes")
    public String findAll(Model model){
        List<Class> classes = classService.findAll();
        model.addAttribute("classes", classes);
        return "class-list";
    }
    @GetMapping("/clas-create")
    public  String createClassForm(Class class_){
        return "clas-create";
    }

    @PostMapping("/clas-create")
    public  String createClass(Class class_){
        classService.saveClass(class_);
        return "redirect:/classes";
    }

    @GetMapping("class-delete/{id_c}")
    public String deleteClass(@PathVariable("id_c") Long id_c){
        classService.deleteById(id_c);
        return "redirect:/classes";
    }
    // add slash before user-update
    @GetMapping("class-update/{id_c}")
    public String updateClassForm(@PathVariable("id_c") Long id_c, Model model){
        Class aClass = classService.findById(id_c);
        model.addAttribute("aClass", aClass);
        return "class-update";
    }

    @PostMapping("/class-update")
    public String updateClass(Class aClass){
        classService.saveClass(aClass);
        return "redirect:/classes";
    }
}
