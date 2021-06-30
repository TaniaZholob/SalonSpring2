package com.tania.zholob.demo.controllers;

import com.tania.zholob.demo.model.entity.Roles;
import com.tania.zholob.demo.model.entity.Users;
import com.tania.zholob.demo.model.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Registration {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public String addUser(@ModelAttribute("users") Users user, Model model) {
        System.out.println("Got User: " + user);
        Users u = userRepo.findByUsername(user.getUsername());
        if (u != null) {
            model.addAttribute("message", "User exist");
            return "register";
        }
        user.setRole(Roles.CLIENT);
        userRepo.save(user);
        return "redirect:/login";
    }


    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("users", new Users());
        return "register";
    }
}
