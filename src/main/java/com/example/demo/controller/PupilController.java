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

    @Autowired
    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }
    @GetMapping("/pupils")
    public String findAll(Model model){
        List<Pupil> pupils = pupilService.findAll();
        model.addAttribute("pupils", pupils);
        return "pupil-list";
    }
    @GetMapping("/pupil-create")
    public String createPupilForm(Pupil pupil){
        return "/pupil-create";
    }

    @PostMapping("/pupil-create")
    public String createPupil(Pupil pupil){
        pupilService.savePupil(pupil);
        return "redirect:/pupils";
    }

    @GetMapping("pupil-delete/{id_p}")
    public String deletePupil(@PathVariable("id_p") Long id){
        pupilService.deleteById(id);
        return "redirect:/pupils";
    }

    @GetMapping("pupil-update/{id_p}")
    public String updatePupilForm(@PathVariable("id_p") Long id, Model model){
        Pupil pupil = pupilService.findById(id);
        model.addAttribute("pupil", pupil);
        return "pupil-update";
    }
    @PostMapping("/pupil-update")
    public String updatePupil(Pupil pupil){
        pupilService.savePupil(pupil);
        return "redirect:/pupils";
    }

}
