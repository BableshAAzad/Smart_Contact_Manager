package com.bablesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bablesh.entity.User;
import com.bablesh.forms.UserForm;
import com.bablesh.helper.Message;
import com.bablesh.helper.MessageType;
import com.bablesh.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

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
    public String signupPage(Model model) {
        UserForm userForm = new UserForm();
        // userForm.setName("Bablesh");
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    // ^ processing register
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        if (rBindingResult.hasErrors()) {
        return "signup";
        }

        // TODO::Validate userForm[Next Video]

        // save to database

        // userservice

        // UserForm--> User
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic(
        // "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic(
                "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

        User savedUser = userService.saveUser(user);

        System.out.println("user saved :");

        // message = "Registration Successful"

        // add the message:

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirectto login page
        return "redirect:/signup";
    }

}
