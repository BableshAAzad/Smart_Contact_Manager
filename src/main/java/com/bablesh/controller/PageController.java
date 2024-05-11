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

}
