package com.example.ecommerce.controller;

import com.example.ecommerce.model.ContactForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//public class ContactController {
//
////    @GetMapping("/contact")
////    public String about(HttpServletRequest request, Model model) {
////        model.addAttribute("currentPath", request.getRequestURI());
////        return "contact";
////    }
//
//    @GetMapping("/contact")
//    public String about(HttpServletRequest request, Model model) {
//        model.addAttribute("currentPath", request.getRequestURI());
//
//        // ✅ FIX: Add contactForm to the model
//        if (!model.containsAttribute("contactForm")) {
//            model.addAttribute("contactForm", new ContactForm());
//        }
//
//        return "contact";
//    }
//
//
//    @PostMapping("/contact")
//    public String submitContact(@Valid @ModelAttribute ContactForm contactForm,
//                                BindingResult result,
//                                RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "contact";
//        }
//
//        // Process the contact form (save to database, send email, etc.)
//        // For demonstration, we'll just add a success message
//        redirectAttributes.addFlashAttribute("successMessage",
//                "Thank you for your message! We'll get back to you soon.");
//
//        return "redirect:/contact";
//    }
//}

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String contact(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());

        // ✅ Add this line to ensure form binding works correctly
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }

        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("contactForm") ContactForm contactForm,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Keep validation errors for redisplay
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", result);
            redirectAttributes.addFlashAttribute("contactForm", contactForm);
            return "redirect:/contact";
        }

        redirectAttributes.addFlashAttribute("successMessage",
                "Thank you for your message! We'll get back to you soon.");

        return "redirect:/contact";
    }
}
