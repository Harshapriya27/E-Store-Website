package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/checkout")
@SessionAttributes("cart")
public class CheckoutController {

    private final OrderService orderService;

    @Autowired
    public CheckoutController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("cart") // Initialize the cart if it doesn't exist in session
    public ShoppingCart cart() {
        return new ShoppingCart();
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "checkout"; // or whatever your checkout page is
    }


//    @GetMapping
//    public String showCheckoutForm(@ModelAttribute("cart") ShoppingCart cart, HttpSession session, Model model) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            session.setAttribute("redirectAfterLogin", "/checkout");
//            return "redirect:/login";
//        }
//
//        if (cart == null || cart.getItems().isEmpty()) {
//            return "redirect:/cart";
//        }
//
//        model.addAttribute("cart", cart);
//        return "checkout/form";
//    }

    @GetMapping
    public String showCheckoutForm(@ModelAttribute("cart") ShoppingCart cart, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("redirectAfterLogin", "/checkout");
            return "redirect:/login";
        }

        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("user", user); // ✅ Add user to model
        return "checkout/form";
    }



    @PostMapping("/process")
    public String processCheckout(
            @RequestParam String customerName,
            @RequestParam String customerEmail,
            @RequestParam String shippingAddress,
            @ModelAttribute("cart") ShoppingCart cart,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        if (cart == null || cart.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty.");
            return "redirect:/cart";
        }

        try {
            User user = (User) session.getAttribute("user"); // ✅ Get logged-in user
            if (user == null) {
                return "redirect:/login"; // Safety check
            }

            // ✅ Create order linked to user
            Order order = orderService.createOrder(user, customerName, customerEmail, shippingAddress, cart);

            cart.clear(); // Clear cart after order

            redirectAttributes.addFlashAttribute("orderId", order.getId());
            return "redirect:/checkout/confirmation";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error processing your order: " + e.getMessage());
            return "redirect:/checkout";
        }
    }


    @GetMapping("/confirmation")
    public String showConfirmation(Model model, @ModelAttribute("orderId") Long orderId) {
        if (orderId == null) {
            return "redirect:/";
        }

        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        model.addAttribute("order", order);
        return "checkout/confirmation";
    }
}

