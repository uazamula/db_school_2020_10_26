package com.example.demo.controller;

import com.example.demo.model.Pupil;
import com.example.demo.service.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PupilController {
    private final PupilService pupilService;
    private final String s="pupil";
    private final String ss="pupils";
    private final String IDS="id_p";

    @Autowired
    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }
   // @GetMapping("/pupils")
    @GetMapping(ss)
    public String findAll(Model model){
        List<Pupil> pupils = pupilService.findAll();
        model.addAttribute(ss, pupils);
        return s+"-list";
    }
    @GetMapping(s+"-create")
    public String createPupilForm(Pupil pupil){
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
        return s+"-update";
    }
    @PostMapping(s+"-update")
    public String updatePupil(Pupil pupil){
        pupilService.savePupil(pupil);
        return "redirect:/"+ss;
    }

}
