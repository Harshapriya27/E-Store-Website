package com.example.ecommerce.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

//    @GetMapping("/about")
//    public String about(Model model) {
//        model.addAttribute("pageTitle", "About Us");
//        model.addAttribute("companyName", "E-commerce Store");
//        model.addAttribute("foundedYear", "2020");
//        model.addAttribute("employeeCount", "50+");
//        return "about";
//    }

    @GetMapping("/about")
    public String about(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        return "about";
    }

}