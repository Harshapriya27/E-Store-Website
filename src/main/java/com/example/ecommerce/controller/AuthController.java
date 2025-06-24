package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    // Custom login page (GET)
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "type", defaultValue = "user") String type,
                            Model model) {
        model.addAttribute("loginType", type);
        return "login"; // maps to login.html
    }

    // Login processing (POST)
    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(required = false) String loginType,
                               HttpSession session,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        Optional<User> userOpt = userService.authenticateUser(email, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Determine role if not explicitly passed
            if (loginType == null) {
                loginType = "USER".equalsIgnoreCase(user.getRole()) ? "user" : "admin";
            }

            // Role mismatch checks
            if ("admin".equals(loginType) && !"ADMIN".equalsIgnoreCase(user.getRole())) {
                model.addAttribute("error", "Access denied. Admin credentials required.");
                model.addAttribute("loginType", loginType);
                return "login";
            }

            if ("user".equals(loginType) && "ADMIN".equalsIgnoreCase(user.getRole())) {
                model.addAttribute("error", "Please use admin login.");
                model.addAttribute("loginType", loginType);
                return "login";
            }

            // Set session
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole());

            // Redirect to originally requested page
            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
            if (redirectUrl != null) {
                session.removeAttribute("redirectAfterLogin");
                return "redirect:" + redirectUrl;
            }

            // Default dashboard redirection
            return "ADMIN".equalsIgnoreCase(user.getRole())
                    ? "redirect:/admin/dashboard"
                    : "redirect:/user/dashboard";
        }

        model.addAttribute("error", "Invalid email or password.");
        model.addAttribute("loginType", loginType != null ? loginType : "user");
        return "login";
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
                                      Model model,
                                      RedirectAttributes redirectAttributes) {

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
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
            return "redirect:/login"; // ✅ Redirect to avoid 403 error
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
//    @GetMapping("/dashboard")
//    public String userDashboard(HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//
//        if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
//            session.setAttribute("redirectAfterLogin", "/dashboard");
//            return "redirect:/login";
//        }
//
//        List<Order> userOrders = orderService.getOrdersByUser(user);
//        model.addAttribute("orders", userOrders);
//        model.addAttribute("user", user);
//        return "user-dashboard";
//    }

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

    @GetMapping("/user/orders")
    public String viewOrders(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"USER".equalsIgnoreCase(user.getRole())) {
            session.setAttribute("redirectAfterLogin", "/user/orders");
            return "redirect:/login";
        }

        List<Order> userOrders = orderService.getOrdersByUser(user);
        model.addAttribute("orders", userOrders);
        return "user/orders";
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

    @GetMapping("/admin/users")
    public String manageUsers(HttpSession session, Model model) {
        User admin = (User) session.getAttribute("user");
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            return "redirect:/login?type=admin";
        }

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }
}
