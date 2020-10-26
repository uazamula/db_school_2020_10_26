package com.example.demo.controller;

import com.example.demo.model.Class;
import com.example.demo.model.Pupil;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.ClassService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassController {
    private final ClassService classService;
    private final UserService userService;

    @Autowired
    public ClassController(ClassService classService, UserService userService) {
        this.classService = classService;
        this.userService = userService;
    }
    @GetMapping("/classes")
    public String findAll(Model model){
        List<Class> classes = classService.findAll();

        List<User> users = userService.findAll();////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
        List<ClassNew> classesNew = new ArrayList<>();
/////////////////////////////////////////////////////////////////////////////////////////////
        for(Class eClass:classes) {
            int classInt = eClass.getClassInt();
            char classChar = eClass.getClassChar();
            String s =" No teacher has been assigned yet";
            if (eClass.getTeacherId()==null)
                System.out.println(classInt + "" + classChar + s);
            else{
                for (User eUser : users) {
                    if (eClass.getTeacherId()== eUser.getId()) {
                        s = eUser.getLastName() + " " + eUser.getFirstName();
                        System.out.println(classInt + "" + classChar + " " + s);
                    }
                }
            }
            ClassNew classNew = new ClassNew(
                    eClass.getId(),
                    eClass.getClassInt(),
                    eClass.getClassChar(),
                    s);
            classesNew.add(classNew);
        }
        model.addAttribute("classes", classesNew);
        return "class-list";
    }
    @GetMapping("/clas-create")
    public  String createClassForm(Class class_,Model model, User user){
        List<User> users = userService.findAll();////////////////////////////////////////////
        model.addAttribute("teachers", users);////////////////////////////////////
        for(User e:users)
            System.out.println(e.getLastName() + e.getFirstName());
        return "clas-create";
    }

    @PostMapping("/clas-create")
    public  String createClass(Class class_){
        classService.saveClass(class_);
        return "redirect:/classes";
    }

    @GetMapping("class-delete/{id}")
    public String deleteClass(@PathVariable("id") Long id){
        classService.deleteById(id);
        return "redirect:/classes";
    }
    // add slash before user-update
    @GetMapping("class-update/{id}")
    public String updateClassForm(@PathVariable("id") Long id, Model model){
        Class aClass = classService.findById(id);
        model.addAttribute("aClass", aClass);
        return "class-update";
    }

    @PostMapping("/class-update")
    public String updateClass(Class aClass){
        classService.saveClass(aClass);
        return "redirect:/classes";
    }
}