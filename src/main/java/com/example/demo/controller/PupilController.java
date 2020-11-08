package com.example.demo.controller;

import com.example.demo.model.Class;
import com.example.demo.model.Pupil;
import com.example.demo.service.ClassService;
import com.example.demo.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class PupilController {
    private final PupilService pupilService;
    private final ClassService classService;
    private final String s="pupil";
    private final String ss="pupils";
    private final String IDS="id";

    @Autowired
    public PupilController(PupilService pupilService, ClassService classService) {
        this.pupilService = pupilService;
        this.classService = classService;
    }
   // @GetMapping("/pupils")
    @GetMapping(ss)
    public String findAll(Model model){
        List<Pupil> pupils = pupilService.findAll();
        Collections.sort(pupils);
        model.addAttribute(ss, pupils);
        List<Class> classes = classService.findAll();/////////////////////////
        model.addAttribute("classes", classes);////////////////
        return s+"-list";
    }
    @GetMapping(s+"-create")
    public String createPupilForm(Pupil pupil, Model model){
        List<Class> classes = classService.findAll();/////////////////////////
        Collections.sort(classes);
        model.addAttribute("classes", classes);////////////////
        return s+"-create";
    }

    @PostMapping(s+"-create")
    public String createPupil(Pupil pupil){
        pupilService.savePupil(pupil);
        return "redirect:/"+ss;
    }

    @GetMapping(s+"-delete/{"+IDS+"}")
    public String deletePupil(@PathVariable(IDS) Long id){
        pupilService.deleteById(id);
        return "redirect:/"+ss;
    }

    @GetMapping(s+"-update/{"+IDS+"}")
    public String updatePupilForm(@PathVariable(IDS) Long id, Model model){
        Pupil pupil = pupilService.findById(id);
        model.addAttribute(s, pupil);
        List<Class> classes = classService.findAll();/////////////////////////
        Collections.sort(classes);
        model.addAttribute("classes", classes);////////////////
        return s+"-update";
    }
    @PostMapping(s+"-update")
    public String updatePupil(Pupil pupil){
        pupilService.savePupil(pupil);
        return "redirect:/"+ss;
    }

}
