package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.Class;
import com.example.demo.service.ClassService;
import com.example.demo.service.GradeService;
import com.example.demo.service.PupilService;
import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class GradeController {

    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final PupilService pupilService;
    private final ClassService classService;
    private Subject e;

    @Autowired
    public GradeController(GradeService gradeService,
                           SubjectService subjectService,
                           PupilService pupilService,
                           ClassService classService) {
        this.gradeService = gradeService;
        this.subjectService = subjectService;
        this.pupilService = pupilService;
        this.classService =classService;
    }
    @GetMapping("/grades")
    public String findAll(Model model)
    {
        gradeNew(model);
        return "grade-list";
    }

    @GetMapping("/grade-create")
    public String createGradeFormNew(Grade grade, Model model, Subject subject, Pupil pupil){
        List<Subject> subjects = subjectService.findAll();////////////////////////////////////////////
//        Collections.sort(subjects);
//        model.addAttribute("subjects", subjects);////////////////////////////////////

        List<Pupil> pupils = pupilService.findAll();////////////////////////////////////////////
        Collections.sort(pupils);
        model.addAttribute("pupils", pupils);////////////////////////////////////

        List<Class> classes = classService.findAll();/////////////////////////
        model.addAttribute("classes", classes);////////////////

        List<SubjectNew> subjectNewList = new ArrayList<>();
        Map<Long,String> mapClasses = new HashMap<>();
        for (Class eClass : classes) {
            String className = eClass.getClassInt() + "" + eClass.getClassChar();
            if(eClass.getClassInt()<10)
                className = " " + className;
            mapClasses.put(eClass.getId(), className);
        }

        for(Subject eSubject : subjects) {
            String className=null;
            if (eSubject.getClassId()!=null) {
                className = mapClasses.get(eSubject.getClassId());

                SubjectNew subjectNew = new SubjectNew(
                        eSubject.getId(),
                        eSubject.getSubjectName(),
                        eSubject.getClassId(), className,
                        eSubject.getTeacherId(), "Add a bit of code to output teacher's name here");

                subjectNewList.add(subjectNew);
            }
        }
        Collections.sort(subjectNewList);
        model.addAttribute("subjectNewList", subjectNewList);

        //gradeNew(model);

        return "/grade-create";
    }

    @PostMapping("/grade-create")
    public String createGrade(Grade grade, Model model, Subject subject){
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




    public void gradeNew(Model model){
        List<GradeNew> gradeNewList = new ArrayList<>();

        List<Grade> grades = gradeService.findAll();
        List<Subject> subjects = subjectService.findAll();////////////////////////////////////////////
        List<Pupil> pupils = pupilService.findAll();////////////////////////////////////////////
        List<Class> classes = classService.findAll();/////////////////////////
        Collections.sort(subjects);
        Collections.sort(pupils);
        Collections.sort(classes);

        Map<Long,Object[]> mapPupils = new HashMap<>();
        for (Pupil ePupil : pupils)
            mapPupils.put(ePupil.getId(),new Object[]{
                    ePupil.getLastName(),ePupil.getFirstName(),ePupil.getClassP()});

        Map<Long,String[]> mapClasses = new HashMap<>();
        for (Class eClass : classes)
            mapClasses.put(eClass.getId(),new String[]{
                    eClass.getClassInt()+"",eClass.getClassChar()+""});

        Map<Long,Object[]> mapSubjects = new HashMap<>();
        for (Subject eSubject : subjects)
            mapSubjects.put(eSubject.getId(), new Object[]
                    {eSubject.getSubjectName(),eSubject.getClassId()});

        for(Grade eGrade : grades) {
            String pupilFirstName ="";
            String pupilLastName ="--Не обрано--";
            Long classId=null;
            int classInt=0;
            char classChar=' ';
            String subjectName="";
            if (eGrade.getPupil()!=null) {
                pupilLastName = (String)mapPupils.get(eGrade.getPupil())[0];
                pupilFirstName = (String)mapPupils.get(eGrade.getPupil())[1];
            }
            if (eGrade.getSubject()!=null) {
                subjectName = (String)mapSubjects.get(eGrade.getSubject())[0];
                classId = (Long)mapSubjects.get(eGrade.getSubject())[1];
                if (classId!=null){
                        classInt=Integer.parseInt(mapClasses.get(classId)[0]);
                        classChar=(mapClasses.get(classId)[1]).charAt(0);
                    }
            }


            GradeNew gradeNew = new GradeNew(
                    eGrade.getId(),
                    classId, classInt, classChar,
                    eGrade.getPupil(), pupilFirstName, pupilLastName,
                    eGrade.getSubject(), subjectName,
                    eGrade.getGradePupil());
            gradeNewList.add(gradeNew);
        }

        Collections.sort(gradeNewList);
        model.addAttribute("gradeNewList", gradeNewList);


        model.addAttribute("grades", grades);
        model.addAttribute("subjects", subjects);////////////////////////////////////
        model.addAttribute("pupils", pupils);////////////////////////////////////
        model.addAttribute("classes", classes);////////////////


    }
}
