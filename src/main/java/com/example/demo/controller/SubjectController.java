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

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class SubjectController {
    private final SubjectService subjectService;
    private final ClassService classService;
    private final UserService userService;

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    public SubjectController(SubjectService subjectService, ClassService classService, UserService userService) {
        this.subjectService = subjectService;
        this.classService = classService;
        this.userService = userService;
    }
    @GetMapping("/subjects")
    public String findAll(Model model){
        LocalDateTime t1,t2;    int sec1,sec2,diff;    t1=LocalDateTime.now();
        sec1=t1.getNano();   System.out.println("time begin: " + t1 + "   nanosec:" + sec1 );
        int pageSize=5;
        int pageNo = 1;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//                      Variant2
        Query q = em.createNativeQuery(
     "SELECT a.id_s, a.subject_name, b.first_name, b.last_name, c.class_int, c.class_char FROM subjects a LEFT JOIN users b ON a.teacher_id = b.id LEFT JOIN classes c ON a.class_id = c.id_c ORDER BY c.class_int LIMIT ?,?" );
        q.setParameter(1, (pageNo-1)*pageSize);
        q.setParameter(2, pageSize);

        List<Object[]> subjects = q.getResultList();
        model.addAttribute("subjects", subjects);
        //List<>
        for(Object[] a : subjects){

            System.out.println(a[0] + " " + a[1] + " " +a[2] + " " +a[3]+ " " +a[4] + " " +a[5]);
        }
        em.getTransaction().commit();
        em.close();

        t2=LocalDateTime.now();    sec2=t2.getNano();
        System.out.println("time end: " + t2 + "   nanosec:" + sec2 );
        System.out.println("duration(seconds): " + (sec2 - sec1)/1e+9 );


        //subjectNew(model);

        return "subject-list";
    }
    @GetMapping("/subject-create")
    public String createSubjectForm(Subject subject, Model model){
        classesAndTeachersSorted(model);
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
       classesAndTeachersSorted(model);
        return "subject-update";
    }
    @PostMapping("/subject-update")
    public String updateSubject(Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    public void classesAndTeachersSorted(Model model){
        List<Class> classes = classService.findAll();/////////////////////////
        Collections.sort(classes);
        model.addAttribute("classes", classes);////////////////
        List<User> users = userService.findAll();////////////////////////////////////////////
        Collections.sort(users);
        model.addAttribute("teachers", users);////////////////////////////////////
    }

    public void subjectNew(Model model){

/////////////////////////////////////////////////////////////////////////////////////////////
        LocalDateTime t1,t2;    int sec1,sec2,diff;    t1=LocalDateTime.now();
        sec1=t1.getNano();   System.out.println("time begin: " + t1 + "   nanosec:" + sec1 );

        List<Subject> subjects = subjectService.findAll();
        List<Class> classes = classService.findAll();
        List<User> users = userService.findAll();

        List<SubjectNew> subjectNewList = new ArrayList<>();

        Map<Long,String> mapTeacher = new HashMap<>();
        for (User eUser : users)
            mapTeacher.put(eUser.getId(),
                    eUser.getLastName() + " " + eUser.getFirstName());

        Map<Long,String> mapClasses = new HashMap<>();
        for (Class eClass : classes) {
            String className = eClass.getClassInt() + "" + eClass.getClassChar();
            if(eClass.getClassInt()<10)
                className = " " + className;
            mapClasses.put(eClass.getId(), className);
        }

        for(Subject eSubject : subjects) {
            String className=null;
            String teacherName=null;
            if (eSubject.getClassId()!=null)
                className = mapClasses.get(eSubject.getClassId());
            if(eSubject.getTeacherId()!=null)
                teacherName=mapTeacher.get(eSubject.getTeacherId());

            SubjectNew subjectNew = new SubjectNew(
                    eSubject.getId(),
                    eSubject.getSubjectName(),
                    eSubject.getClassId(), className,
                    eSubject.getTeacherId(), teacherName);

            subjectNewList.add(subjectNew);

        }

        t2=LocalDateTime.now();    sec2=t2.getNano();
        System.out.println("time end: " + t2 + "   nanosec:" + sec2 );
        System.out.println("duration(seconds): " + (sec2 - sec1)/1e+9 );

        Collections.sort(subjectNewList);
        model.addAttribute("subjectNewList", subjectNewList);
        model.addAttribute("classes", classes);
        model.addAttribute("teachers", users);
    }
}
