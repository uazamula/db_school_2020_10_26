package com.example.demo.controller;

import com.example.demo.model.Class;
import com.example.demo.model.Pupil;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.ClassService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class ClassController {
    private final ClassService classService;
    private final UserService userService;
    private String numberOfPage;
    //fClasses1 Error message when exception is thrown
    private int errmsg=0;

    @Autowired
    public ClassController(ClassService classService, UserService userService) {
        this.classService = classService;
        this.userService = userService;
    }
    @GetMapping("/classesold")
    public String findAll(Model model){
        List<Class> classes = classService.findAll();

        List<User> users = userService.findAll();////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
        List<ClassNew> classesNew = new ArrayList<>();
/////////////////////////////////////////////////////////////////////////////////////////////
        LocalDateTime t1,t2;
        int sec1,sec2,diff;
        t1=LocalDateTime.now();
        sec1=t1.getNano();
        System.out.println("time begin: " + t1 + "   nanosec:" + sec1 );

        Map<Long,String> mapTeacher = new HashMap<>();
        for (User eUser : users)
            mapTeacher.put(eUser.getId(),
                    eUser.getLastName() + " " + eUser.getFirstName());

        for(Class eClass:classes) {
            int classInt = eClass.getClassInt();
            char classChar = eClass.getClassChar();
            String teachersFullName ="--Не обрано--";
            if (eClass.getTeacherId()==null)
                System.out.println(classInt + "" + classChar + teachersFullName);
            else{
                teachersFullName = mapTeacher.get(eClass.getTeacherId());

//                for (User eUser : users) {
//                    if (eClass.getTeacherId()== eUser.getId()) {
//                        teachersFullName = eUser.getLastName() + " " + eUser.getFirstName();
//                       // System.out.println(classInt + "" + classChar + " " + teachersFullName);
//                    }
//                }
            }
            ClassNew classNew = new ClassNew(
                    eClass.getId(),
                    classInt,
                    classChar,
                    teachersFullName);
            classesNew.add(classNew);
        }

        t2=LocalDateTime.now();
        sec2=t2.getNano();
        System.out.println("time end: " + t2 + "   nanosec:" + sec2 );
        System.out.println("duration(seconds): " + (sec2 - sec1)/1e+9 );

        Collections.sort(classesNew);

       /* EntityManager em = emf.createEntityManager();    em.getTransaction().begin();
        List<Object[]> results = em.createQuery("SELECT p.firstName, p.lastName, n.phoneNumber FROM Person p, PhoneBookEntry n WHERE p.firstName = n.firstName AND p.lastName = n.lastName").getResultList();
        for (Object[] result : results) {log.info(result[0] + " " + result[1] + " - " + result[2]);   }      em.getTransaction().commit();       em.close();
        */
        model.addAttribute("classes", classesNew);
//        for(ClassNew e:classesNew) System.out.println(e.getTeacher());
        return "class-list";
    }
    @GetMapping("/clas-create")
    public  String createClassForm(Class class_,Model model, User user){
        List<User> users = userService.findAll();////////////////////////////////////////////
        Collections.sort(users);
        model.addAttribute("teachers", users);////////////////////////////////////
        //fClasses1 Error message when exception is thrown
        errmsg=0;
        return "clas-create";
    }

    @PostMapping("/clas-create")
    public  String createClass(Class class_,Model model){
        //fClasses1 Error message when exception is thrown
        try {
            classService.saveClass(class_);
        }
        catch (Exception e){
            errmsg = 1;//"try other values";
            model.addAttribute("errmsg", errmsg);////////////////////////////////////
            return "clas-create";
        }
        return "redirect:/classes"+numberOfPage;
    }

    @GetMapping("class-delete/{id}")
    public String deleteClass(@PathVariable("id") Long id){
        classService.deleteById(id);
        return "redirect:/classes"+numberOfPage;
    }
    // add slash before user-update
    @GetMapping("class-update/{id}")
    public String updateClassForm(@PathVariable("id") Long id, Model model){
        Class aClass = classService.findById(id);
        model.addAttribute("aClass", aClass);
        List<User> users = userService.findAll();////////////////////////////////////////////
        Collections.sort(users);
        model.addAttribute("teachers", users);////////////////////////////////////
        return "class-update";
    }

    @PostMapping("/class-update")
    public String updateClass(Class aClass){
        classService.saveClass(aClass);
        return "redirect:/classes"+numberOfPage;
    }
    @GetMapping("classes"+"{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){
        int pageSize = 5;
        Page<Class> page = classService.findPaginated(pageNo, pageSize,
                                                        sortField, sortDir);
        List<Class> classList = page.getContent();

        classesNew(model,classList);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("pageSize", pageSize);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        numberOfPage=Integer.toString(pageNo)+"?sortField="+sortField+"&sortDir="+sortDir;
        return "index-classes";
    }
    @GetMapping("/classes")
    public String viewHomePage(Model model){
        return findPaginated(1, "classInt", "asc", model);
    }


    public void classesNew(Model model, List<Class> classes){
        List<User> users = userService.findAll();////////////////////////////////////////////
        List<ClassNew> classesNew = new ArrayList<>();
        Map<Long,String> mapTeacher = new HashMap<>();
        for (User eUser : users)
            mapTeacher.put(eUser.getId(),
                    eUser.getLastName() + " " + eUser.getFirstName());

        for(Class eClass:classes) {
            int classInt = eClass.getClassInt();
            char classChar = eClass.getClassChar();
            String teachersFullName ="--Не обрано--";
            if (eClass.getTeacherId()!=null)
                teachersFullName = mapTeacher.get(eClass.getTeacherId());
            ClassNew classNew = new ClassNew(
                    eClass.getId(),
                    classInt,
                    classChar,
                    teachersFullName);
            classesNew.add(classNew);
        }
        model.addAttribute("classList", classesNew);
    }
}