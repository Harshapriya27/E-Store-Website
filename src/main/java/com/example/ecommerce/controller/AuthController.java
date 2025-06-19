package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

//    // Home page
//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }

    // Login page
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "type", defaultValue = "user") String type, Model model) {
        model.addAttribute("loginType", type);
        return "login";
    }

    // Process login
    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String loginType,
                               HttpSession session,
                               Model model) {

        Optional<User> userOpt = userService.authenticateUser(email, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Check if login type matches user role
            if ("admin".equals(loginType) && !"ADMIN".equals(user.getRole())) {
                model.addAttribute("error", "Access denied. Admin credentials required.");
                model.addAttribute("loginType", loginType);
                return "login";
            }

            if ("user".equals(loginType) && "ADMIN".equals(user.getRole())) {
                model.addAttribute("error", "Please use admin login.");
                model.addAttribute("loginType", loginType);
                return "login";
            }

            // Set session attributes
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole());

            // Redirect based on role
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            model.addAttribute("loginType", loginType);
            return "login";
        }
    }

    // Registration page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Process registration
    @PostMapping("/register")
    public String processRegistration(@RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam String confirmPassword,
                                      @RequestParam String firstName,
                                      @RequestParam String lastName,
                                      Model model) {

        // Validation
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        if (userService.emailExists(email)) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }

        try {
            User user = new User(email, password, firstName, lastName, "USER");
            userService.registerUser(user);
            model.addAttribute("success", "Registration successful! Please login.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // User Dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"USER".equals(user.getRole())) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "user-dashboard";
    }

    // Admin Dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/login?type=admin";
        }
        model.addAttribute("user", user);
        return "admin-dashboard";
    }
}
