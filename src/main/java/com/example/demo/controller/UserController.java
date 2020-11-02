package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
//@RequestMapping("/teachers")//
public class UserController {
    private final UserService userService;
    private final String s="teacher";
    private final String ss="teachers";
    private final String IDS="id";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ss)
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
        return "redirect:/"+ss;
    }

    @GetMapping(s+"-delete/{"+IDS+"}")
    public String deleteUser(@PathVariable(IDS) Long id){
        userService.deleteById(id);
        return "redirect:/"+ss;
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
        return "redirect:/"+ss;
    }
}
