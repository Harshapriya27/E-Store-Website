//package com.example.ecommerce.controller;
//
//import com.example.ecommerce.model.Order;
//import com.example.ecommerce.model.ShoppingCart;
//import com.example.ecommerce.service.OrderService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
////
////@Controller
////@RequestMapping("/checkout")
////public class CheckoutController {
////
////    private final OrderService orderService;
////
////    @Autowired
////    public CheckoutController(OrderService orderService) {
////        this.orderService = orderService;
////    }
////
////    @GetMapping
////    public String showCheckoutForm(HttpSession session, Model model) {
////        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
////
////        if (cart == null || cart.getItems().isEmpty()) {
////            return "redirect:/cart";
////        }
////
////        model.addAttribute("cart", cart);
////        return "checkout/form";
////    }
////
////    @PostMapping("/process")
////    public String processCheckout(
////            @RequestParam String customerName,
////            @RequestParam String customerEmail,
////            @RequestParam String shippingAddress,
////            HttpSession session,
////            RedirectAttributes redirectAttributes) {
////
////        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
////
////        if (cart == null || cart.getItems().isEmpty()) {
////            redirectAttributes.addFlashAttribute("error", "Your cart is empty.");
////            return "redirect:/cart";
////        }
////
////        try {
////            Order order = orderService.createOrder(customerName, customerEmail, shippingAddress, cart);
////
////            // Clear the cart after successful order
////            cart.clear();
////            session.setAttribute("cart", cart);
////
////            redirectAttributes.addFlashAttribute("orderId", order.getId());
////            return "redirect:/checkout/confirmation";
////        } catch (Exception e) {
////            redirectAttributes.addFlashAttribute("error", "Error processing your order: " + e.getMessage());
////            return "redirect:/checkout";
////        }
////    }
////
////    @GetMapping("/confirmation")
////    public String showConfirmation(Model model) {
////        Long orderId = (Long) model.asMap().get("orderId");
////
////        if (orderId == null) {
////            return "redirect:/";
////        }
////
////        Order order = orderService.getOrderById(orderId)
////                .orElseThrow(() -> new RuntimeException("Order not found"));
////
////        model.addAttribute("order", order);
////        return "checkout/confirmation";
////    }
////}
//
//
////package com.example.ecommerce.controller;
////
////import com.example.ecommerce.model.Order;
////import com.example.ecommerce.model.ShoppingCart;
////import com.example.ecommerce.service.OrderService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.servlet.mvc.support.RedirectAttributes;
////
////@Controller
////@RequestMapping("/checkout")
////@SessionAttributes("cart")
////public class CheckoutController {
////
////    private final OrderService orderService;
////
////    @Autowired
////    public CheckoutController(OrderService orderService) {
////        this.orderService = orderService;
////    }
////
////    @GetMapping
////    public String showCheckoutForm(@ModelAttribute("cart") ShoppingCart cart, Model model) {
////        if (cart == null || cart.getItems().isEmpty()) {
////            return "redirect:/cart";
////        }
////        model.addAttribute("cart", cart);
////        return "checkout/form";
////    }
////
////    @PostMapping("/process")
////    public String processCheckout(
////            @RequestParam String customerName,
////            @RequestParam String customerEmail,
////            @RequestParam String shippingAddress,
////            @ModelAttribute("cart") ShoppingCart cart,
////            RedirectAttributes redirectAttributes) {
////
////        if (cart == null || cart.getItems().isEmpty()) {
////            redirectAttributes.addFlashAttribute("error", "Your cart is empty.");
////            return "redirect:/cart";
////        }
////
////        try {
////            Order order = orderService.createOrder(customerName, customerEmail, shippingAddress, cart);
////
////            // Clear the cart after successful order
////            cart.clear();
////            redirectAttributes.addFlashAttribute("orderId", order.getId());
////            return "redirect:/checkout/confirmation";
////        } catch (Exception e) {
////            redirectAttributes.addFlashAttribute("error", "Error processing your order: " + e.getMessage());
////            return "redirect:/checkout";
////        }
////    }
////
////    @GetMapping("/confirmation")
////    public String showConfirmation(Model model, @ModelAttribute("orderId") Long orderId) {
////        if (orderId == null) {
////            return "redirect:/";
////        }
////
////        Order order = orderService.getOrderById(orderId)
////                .orElseThrow(() -> new RuntimeException("Order not found"));
////
////        model.addAttribute("order", order);
////        return "checkout/confirmation";
////    }
////
////    @ModelAttribute("orderId")
////    public Long orderId() {
////        return null;
////    }
////}
//
//
//
//@Controller
//@RequestMapping("/checkout")
//@SessionAttributes("cart")
//public class CheckoutController {
//
//    private final OrderService orderService;
//
//    @Autowired
//    public CheckoutController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @GetMapping
//    public String showCheckoutForm(@ModelAttribute("cart") ShoppingCart cart, Model model) {
//        if (cart == null || cart.getItems().isEmpty()) {
//            return "redirect:/cart";
//        }
//        model.addAttribute("cart", cart);
//        return "checkout/form";
//    }
//
//    @PostMapping("/process")
//    public String processCheckout(
//            @RequestParam String customerName,
//            @RequestParam String customerEmail,
//            @RequestParam String shippingAddress,
//            @ModelAttribute("cart") ShoppingCart cart,
//            RedirectAttributes redirectAttributes) {
//
//        if (cart == null || cart.getItems().isEmpty()) {
//            redirectAttributes.addFlashAttribute("error", "Your cart is empty.");
//            return "redirect:/cart";
//        }
//
//        try {
//            Order order = orderService.createOrder(customerName, customerEmail, shippingAddress, cart);
//
//            // Clear the cart after successful order
//            cart.clear(); // Clear the actual cart object
//            // No need to set session attribute manually, @SessionAttributes handles it
//            redirectAttributes.addFlashAttribute("orderId", order.getId());
//            return "redirect:/checkout/confirmation";
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Error processing your order: " + e.getMessage());
//            return "redirect:/checkout";
//        }
//    }
//
//    @GetMapping("/confirmation")
//    public String showConfirmation(Model model, @ModelAttribute("orderId") Long orderId) {
//        if (orderId == null) {
//            return "redirect:/";
//        }
//
//        Order order = orderService.getOrderById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        model.addAttribute("order", order);
//        return "checkout/confirmation";
//    }
//}


package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.ShoppingCart;
import com.example.ecommerce.service.OrderService;
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

    @GetMapping
    public String showCheckoutForm(@ModelAttribute("cart") ShoppingCart cart, Model model) {
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("cart", cart);
        return "checkout/form";
    }

    @PostMapping("/process")
    public String processCheckout(
            @RequestParam String customerName,
            @RequestParam String customerEmail,
            @RequestParam String shippingAddress,
            @ModelAttribute("cart") ShoppingCart cart,
            RedirectAttributes redirectAttributes) {

        if (cart == null || cart.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty.");
            return "redirect:/cart";
        }

        try {
            Order order = orderService.createOrder(customerName, customerEmail, shippingAddress, cart);

            // Clear the cart after successful order
            cart.clear(); // this clears the cart

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

