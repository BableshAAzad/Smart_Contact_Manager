package com.bablesh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String home(Model model) {
        // * Sending data to view
        model.addAttribute("name", "Bablesh AAzad");
        model.addAttribute("Address", "Raipur");
        return "home";
    }
}
