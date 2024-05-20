package com.bablesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {
   // user dashbaord page
    @PostMapping("/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

     // user profile page
    @GetMapping("/profile")
    public String userProfile() {
        System.out.println("User profile");
        return "user/profile";
    }
    
    // user add contacts page

    // user view contacts

    // user edit contact

    // user delete contact
    
}
