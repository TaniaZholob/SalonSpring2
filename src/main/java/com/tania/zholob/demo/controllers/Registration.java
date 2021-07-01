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

    private final UserRepo userRepo;

    public Registration(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("users") Users user, Model model) {
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
