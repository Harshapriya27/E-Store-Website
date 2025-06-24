//package com.example.ecommerce.controller;
//
//import com.example.ecommerce.model.Order;
//import com.example.ecommerce.model.User;
//import com.example.ecommerce.service.OrderService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("/order")
//public class OrderActionController {
//
//    private final OrderService orderService;
//
//    @Autowired
//    public OrderActionController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @PostMapping("/cancel")
//    public String cancelOrder(@RequestParam Long orderId, HttpSession session, RedirectAttributes redirectAttributes) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) return "redirect:/login";
//
//        Order order = orderService.getOrderById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        if (order.getUser().getId().equals(user.getId())) {
//            order.setStatus(Order.OrderStatus.CANCELLED);
//            orderService.saveOrder(order);
//            redirectAttributes.addFlashAttribute("successMessage", "Order #" + orderId + " has been cancelled.");
//        }
//
//        return "redirect:/user/orders";
//    }
//
//    @PostMapping("/return")
//    public String returnOrder(@RequestParam Long orderId, HttpSession session, RedirectAttributes redirectAttributes) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) return "redirect:/login";
//
//        Order order = orderService.getOrderById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        if (order.getUser().getId().equals(user.getId())) {
//            order.setStatus(Order.OrderStatus.RETURNED);
//            orderService.saveOrder(order);
//            redirectAttributes.addFlashAttribute("successMessage", "Order #" + orderId + " has been returned.");
//        }
//
//        return "redirect:/user/orders";
//    }
//
//    @PostMapping("/exchange")
//    public String exchangeOrder(@RequestParam Long orderId, HttpSession session, RedirectAttributes redirectAttributes) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) return "redirect:/login";
//
//        Order order = orderService.getOrderById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        if (order.getUser().getId().equals(user.getId())) {
//            order.setStatus(Order.OrderStatus.EXCHANGED);
//            orderService.saveOrder(order);
//            redirectAttributes.addFlashAttribute("successMessage", "Order #" + orderId + " has been exchanged.");
//        }
//
//        return "redirect:/user/orders";
//    }
//}

package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/order")
public class OrderActionController {

    private final OrderService orderService;

    @Autowired
    public OrderActionController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam Long orderId,
                              @RequestParam(required = false) String reason,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized access.");
            return "redirect:/user/orders";
        }

        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(order.getOrderDate(), now).toDays() > 7) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cancellation window has expired for Order #" + orderId);
            return "redirect:/user/orders";
        }

        order.setStatus(Order.OrderStatus.CANCELLED);

        // Optional: Save reason if your model supports it
        if (reason != null && !reason.isBlank()) {
            order.setCancelReason(reason); // Add this field to Order.java if needed
        }

        orderService.saveOrder(order);

        redirectAttributes.addFlashAttribute("successMessage",
                "Order #" + orderId + " cancelled. Reason: " + reason);

        return "redirect:/user/orders";
    }


    @PostMapping("/return")
    public String returnOrder(@RequestParam Long orderId,
                              @RequestParam(required = false) String reason,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // ✅ Check if the logged-in user owns the order
        if (!order.getUser().getId().equals(user.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized access.");
            return "redirect:/user/orders";
        }

        // ✅ Check if return is within 7 days
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(order.getOrderDate(), now).toDays() > 7) {
            redirectAttributes.addFlashAttribute("errorMessage", "Return window has expired for Order #" + orderId);
            return "redirect:/user/orders";
        }

        // ✅ Update status and (optionally) save the reason
        order.setStatus(Order.OrderStatus.RETURNED);

        // Optional: If you have a field `returnReason` in Order entity
        if (reason != null && !reason.isBlank()) {
            order.setReturnReason(reason);  // Make sure this field exists in your Order entity
        }

        orderService.saveOrder(order);

        redirectAttributes.addFlashAttribute("successMessage",
                "Order #" + orderId + " returned successfully. Reason: " + reason);

        return "redirect:/user/orders";
    }


    @PostMapping("/exchange")
    public String exchangeOrder(@RequestParam Long orderId,
                                @RequestParam(required = false) String reason,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unauthorized access.");
            return "redirect:/user/orders";
        }

        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(order.getOrderDate(), now).toDays() > 7) {
            redirectAttributes.addFlashAttribute("errorMessage", "Exchange window has expired for Order #" + orderId);
            return "redirect:/user/orders";
        }

        order.setStatus(Order.OrderStatus.EXCHANGED);

        // Optional: Save reason if your model supports it
        if (reason != null && !reason.isBlank()) {
            order.setExchangeReason(reason); // Add this field to Order.java if needed
        }

        orderService.saveOrder(order);

        redirectAttributes.addFlashAttribute("successMessage",
                "Order #" + orderId + " exchanged. Reason: " + reason);

        return "redirect:/user/orders";
    }

}
