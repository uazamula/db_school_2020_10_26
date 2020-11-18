package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
//@RequestMapping("/teachers")//
public class UserController {
    private final UserService userService;
    private final String s="teacher";
    private final String ss="teachers";
    private final String IDS="id";
    private String numberOfPage;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ss+"old")
    public String findAll(Model model){
        List<User> users = userService.findAll();

        Collections.sort(users);

        model.addAttribute(ss, users);
        for(User e:users)
            System.out.println(e.toString());
        return s+"-list";
    }
    @GetMapping(s+"-create")
    public  String createUserForm(User user){
        return s+"-create";
    }

    @PostMapping(s+"-create")
    public  String createUser(User user){
        userService.saveUser(user);
        return "redirect:/teachers"+numberOfPage;
    }

    @GetMapping(s+"-delete/{"+IDS+"}")
    public String deleteUser(@PathVariable(IDS) Long id){
        userService.deleteById(id);
        return "redirect:/teachers"+numberOfPage;
    }
// add slash before user-update
    @GetMapping(s+"-update/{"+IDS+"}")
    public String updateUserForm(@PathVariable(IDS) Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute(s, user);
        return s+"-update";
    }

    @PostMapping(s+"-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/teachers"+numberOfPage;
    }

    @Autowired
    private EntityManagerFactory emf;

    @GetMapping("teachers"+"{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                Model model){
        int pageSize = 10;
        Page<User> page = userService.findPaginated(pageNo, pageSize);
        List<User> userList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("userList", userList);
        model.addAttribute("pageSize", pageSize);
        numberOfPage=Integer.toString(pageNo);


        LocalDateTime t1,t2;    int sec1,sec2,diff;    t1=LocalDateTime.now();
        sec1=t1.getNano();   System.out.println("time begin: " + t1 + "   nanosec:" + sec1 );

//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Query q = em.createNativeQuery("SELECT a.id, a.first_name, a.last_name FROM users a LIMIT "
//                + (pageNo-1)*pageSize + ", " + pageSize);
//        List<Object[]> users = q.getResultList();
//        model.addAttribute("teachers", users);
//        for(Object[] a : users){
//            System.out.println(a[0] + " " + a[1] + " " +a[2]);
//        }
//        em.getTransaction().commit();
//        em.close();
//
//        t2=LocalDateTime.now();    sec2=t2.getNano();
//        System.out.println("time end: " + t2 + "   nanosec:" + sec2 );
//        System.out.println("duration(seconds): " + (sec2 - sec1)/1e+9 );
//        ////emf.close();

        return "index-teachers";
    }
    @GetMapping("/teachers")
    public String viewHomePage(Model model){
        return findPaginated(1,model);
    }

}
