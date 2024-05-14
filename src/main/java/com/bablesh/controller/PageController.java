package com.bablesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    // ^ home page
    @GetMapping("/")
    public String home(Model model) {
        // * Sending data to view
        model.addAttribute("name", "Bablesh AAzad");
        model.addAttribute("Address", "Raipur");
        return "home";
    }

    // ^ about page
    @GetMapping("/about")
    public String aboutPage() {
        System.out.println("About Page loading.....");
        return "about";
    }

    // ^ service page
    @GetMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading.....");
        return "services";
    }

    // ^ contact page
    @GetMapping("/contact")
    public String contactPage() {
        System.out.println("Contact page loading.....");
        return "contact";
    }

    // ^ login page
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("Login page loading.....");
        return "login";
    }

    // ^ signup page
    @GetMapping("/signup")
    public String signupPage() {
        System.out.println("singup page loading.....");
        return "signup";
    }

}
