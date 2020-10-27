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
            String TeachersFullName ="--Не обрано--";
            if (eClass.getTeacherId()==null)
                System.out.println(classInt + "" + classChar + TeachersFullName);
            else{
                for (User eUser : users) {
                    if (eClass.getTeacherId()== eUser.getId()) {
                        TeachersFullName = eUser.getLastName() + " " + eUser.getFirstName();
                        System.out.println(classInt + "" + classChar + " " + TeachersFullName);
                    }
                }
            }
            ClassNew classNew = new ClassNew(
                    eClass.getId(),
                    eClass.getClassInt(),
                    eClass.getClassChar(),
                    TeachersFullName);
            classesNew.add(classNew);
        }
        /*
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Object[]> results = em.createQuery("SELECT p.firstName, p.lastName, n.phoneNumber FROM Person p, PhoneBookEntry n WHERE p.firstName = n.firstName AND p.lastName = n.lastName").getResultList();

        for (Object[] result : results) {
            log.info(result[0] + " " + result[1] + " - " + result[2]);
        }

        em.getTransaction().commit();
        em.close();
         */
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
        List<User> users = userService.findAll();////////////////////////////////////////////
        model.addAttribute("teachers", users);////////////////////////////////////
        return "class-update";
    }

    @PostMapping("/class-update")
    public String updateClass(Class aClass){
        classService.saveClass(aClass);
        return "redirect:/classes";
    }
}