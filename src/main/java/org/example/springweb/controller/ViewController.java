package org.example.springweb.controller;

import org.example.springweb.entity.User;
import org.example.springweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String home(){
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        User user = userService.loadCurrentUser();

        System.out.println(user);

        model.addAttribute("user", user);

        return "profile";
    }






}
