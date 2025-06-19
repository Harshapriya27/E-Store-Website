////package com.example.ecommerce.controller;
////
////import com.example.ecommerce.model.Product;
////import com.example.ecommerce.model.ShoppingCart;
////import com.example.ecommerce.service.ProductService;
////import jakarta.servlet.http.HttpSession;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.servlet.mvc.support.RedirectAttributes;
////
////@Controller
////@RequestMapping("/cart")
////@SessionAttributes("cart")
////public class CartController {
////
////    private final ProductService productService;
////
////    @Autowired
////    public CartController(ProductService productService) {
////        this.productService = productService;
////    }
////
////    // Get or create the shopping cart from the session
////    private ShoppingCart getCart(HttpSession session) {
////        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
////        if (cart == null) {
////            cart = new ShoppingCart();
////            session.setAttribute("cart", cart);
////        }
////        return cart;
////    }
////
////    @GetMapping
////    public String viewCart(HttpSession session, Model model) {
////        model.addAttribute("cart", getCart(session));
////        return "cart/view";
////    }
////
////    @PostMapping("/add/{productId}")
////    public String addToCart(
////            @PathVariable Long productId,
////            @RequestParam(defaultValue = "1") int quantity,
////            HttpSession session,
////            RedirectAttributes redirectAttributes) {
////
////        Product product = productService.getProductById(productId)
////                .orElseThrow(() -> new RuntimeException("Product not found"));
////
////        if (product.getStock() < quantity) {
////            redirectAttributes.addFlashAttribute("error",
////                    "Sorry, only " + product.getStock() + " items available in stock.");
////            return "redirect:/products/" + productId;
////        }
////
////        ShoppingCart cart = getCart(session);
////        cart.addItem(product, quantity);
////        session.setAttribute("cart", cart);
////
////        redirectAttributes.addFlashAttribute("success",
////                product.getName() + " added to your cart successfully!");
////        return "redirect:/cart";
////    }
////
////    @PostMapping("/update/{productId}")
////    public String updateCartItem(
////            @PathVariable Long productId,
////            @RequestParam int quantity,
////            HttpSession session,
////            RedirectAttributes redirectAttributes) {
////
////        ShoppingCart cart = getCart(session);
////
////        if (quantity <= 0) {
////            cart.removeItem(productId);
////            redirectAttributes.addFlashAttribute("success", "Item removed from your cart.");
////        } else {
////            Product product = productService.getProductById(productId)
////                    .orElseThrow(() -> new RuntimeException("Product not found"));
////
////            if (product.getStock() < quantity) {
////                redirectAttributes.addFlashAttribute("error",
////                        "Sorry, only " + product.getStock() + " items available in stock.");
////            } else {
////                cart.updateItemQuantity(productId, quantity);
////                redirectAttributes.addFlashAttribute("success", "Cart updated successfully.");
////            }
////        }
////
////        session.setAttribute("cart", cart);
////        return "redirect:/cart";
////    }
////
////    @PostMapping("/remove/{productId}")
////    public String removeCartItem(
////            @PathVariable Long productId,
////            HttpSession session,
////            RedirectAttributes redirectAttributes) {
////
////        ShoppingCart cart = getCart(session);
////        cart.removeItem(productId);
////        session.setAttribute("cart", cart);
////
////        redirectAttributes.addFlashAttribute("success", "Item removed from your cart.");
////        return "redirect:/cart";
////    }
////
////    @PostMapping("/clear")
////    public String clearCart(
////            HttpSession session,
////            RedirectAttributes redirectAttributes) {
////
////        ShoppingCart cart = getCart(session);
////        cart.clear();
////        session.setAttribute("cart", cart);
////
////        redirectAttributes.addFlashAttribute("success", "Your cart has been cleared.");
////        return "redirect:/cart";
////    }
////}
//
//package com.example.ecommerce.controller;
//
//import com.example.ecommerce.model.Product;
//import com.example.ecommerce.model.ShoppingCart;
//import com.example.ecommerce.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//@Controller
//@SessionAttributes("cart")
//public class CartController {
//
//    private final ProductService productService;
//
//    @Autowired
//    public CartController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @ModelAttribute("cart")
//    public ShoppingCart cart() {
//        return new ShoppingCart();
//    }
//
//    @GetMapping("/cart")
//    public String showCart(@ModelAttribute("cart") ShoppingCart cart, Model model) {
//        model.addAttribute("cart", cart);
//        return "cart/view";
//    }
//
//    @PostMapping("/cart/add/{productId}")
//    public String addToCart(@PathVariable Long productId,
//                            @RequestParam(defaultValue = "1") int quantity,
//                            @ModelAttribute("cart") ShoppingCart cart) {
//        Product product = productService.getProductById(productId);
//        if (product == null) {
//            throw new RuntimeException("Product not found");
//        }
//        cart.addItem(product, quantity);
//
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/cart/update/{productId}")
//    public String updateCartItem(@PathVariable Long productId,
//                                 @RequestParam int quantity,
//                                 @ModelAttribute("cart") ShoppingCart cart) {
//        cart.updateItemQuantity(productId, quantity);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/cart/remove/{productId}")
//    public String removeItemFromCart(@PathVariable Long productId,
//                                     @ModelAttribute("cart") ShoppingCart cart) {
//        cart.removeItem(productId);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/cart/clear")
//    public String clearCart(@ModelAttribute("cart") ShoppingCart cart) {
//        cart.clear();
//        return "redirect:/cart";
//    }
//}
//package com.example.ecommerce.controller;
//
//import com.example.ecommerce.model.CartItem;
//import com.example.ecommerce.service.CartService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//@Controller
//@RequestMapping("/cart")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @GetMapping
//    public String viewCart(HttpSession session, Model model) {
//        String sessionId = session.getId();
//        List<CartItem> items = cartService.getCartItems(sessionId);
//        BigDecimal total = cartService.getTotal(sessionId);
//
//        Map<String, Object> cart = new HashMap<>();
//        cart.put("items", items);
//        cart.put("totalAmount", total);
//
//        model.addAttribute("cart", cart);
//        return "cart/view";
//    }
//
//    @PostMapping("/update/{productId}")
//    public String updateQuantity(@PathVariable Long productId,
//                                 @RequestParam int quantity,
//                                 HttpSession session) {
//        cartService.updateQuantity(session.getId(), productId, quantity);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/remove/{productId}")
//    public String removeItem(@PathVariable Long productId, HttpSession session) {
//        cartService.removeItem(session.getId(), productId);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/clear")
//    public String clearCart(HttpSession session) {
//        cartService.clearCart(session.getId());
//        return "redirect:/cart";
//    }
//}
//
//package com.example.ecommerce.controller;
//
//import com.example.ecommerce.model.CartItem;
//import com.example.ecommerce.model.Product;
//import com.example.ecommerce.model.ShoppingCart;
//import com.example.ecommerce.service.ProductService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/cart")
//@SessionAttributes("cart")
//public class CartController {
//
//    private final ProductService productService;
//
//    @Autowired
//    public CartController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    private ShoppingCart getCart(HttpSession session) {
//        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//        if (cart == null) {
//            cart = new ShoppingCart();
//            session.setAttribute("cart", cart);
//        }
//        return cart;
//    }
//
//    @GetMapping
//    public String viewCart(HttpSession session, Model model) {
//        model.addAttribute("cart", getCart(session));
//        return "cart/view";
//    }
//
//    @GetMapping("/add/{productId}")
//    public String addToCart(@PathVariable Long productId,
//                            @RequestParam(defaultValue = "1") int quantity,
//                            HttpSession session,
//                            RedirectAttributes redirectAttributes) {
//        Optional<Product> optionalProduct = productService.getProductById(productId);
//
//        if (optionalProduct.isEmpty()) {
//            redirectAttributes.addFlashAttribute("error", "Product not found.");
//            return "redirect:/products";
//        }
//
//        Product product = optionalProduct.get();
//
//        if (product.getStock() < quantity) {
//            redirectAttributes.addFlashAttribute("error",
//                    "Only " + product.getStock() + " items in stock.");
//            return "redirect:/products";
//        }
//
//        CartItem item = new CartItem(
//                product.getId(),
//                product.getName(),
//                product.getPrice(),
//                quantity,
//                product.getImageUrl()
//        );
//
//        ShoppingCart cart = getCart(session);
//        cart.addItem(item);
//        session.setAttribute("cart", cart);
//
//        redirectAttributes.addFlashAttribute("success", product.getName() + " added to cart.");
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/update/{productId}")
//    public String updateCartItem(@PathVariable Long productId,
//                                 @RequestParam int quantity,
//                                 HttpSession session,
//                                 RedirectAttributes redirectAttributes) {
//
//        ShoppingCart cart = getCart(session);
//
//        if (quantity <= 0) {
//            cart.removeItem(productId);
//            redirectAttributes.addFlashAttribute("success", "Item removed from cart.");
//        } else {
//            Optional<Product> optionalProduct = productService.getProductById(productId);
//
//            if (optionalProduct.isEmpty()) {
//                redirectAttributes.addFlashAttribute("error", "Product not found.");
//            } else if (optionalProduct.get().getStock() < quantity) {
//                redirectAttributes.addFlashAttribute("error", "Not enough stock.");
//            } else {
//                cart.updateItemQuantity(productId, quantity);
//                redirectAttributes.addFlashAttribute("success", "Cart updated.");
//            }
//        }
//
//        session.setAttribute("cart", cart);
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/remove/{productId}")
//    public String removeCartItem(@PathVariable Long productId,
//                                 HttpSession session,
//                                 RedirectAttributes redirectAttributes) {
//        ShoppingCart cart = getCart(session);
//        cart.removeItem(productId);
//        session.setAttribute("cart", cart);
//
//        redirectAttributes.addFlashAttribute("success", "Item removed.");
//        return "redirect:/cart";
//    }
//
//    @PostMapping("/clear")
//    public String clearCart(HttpSession session,
//                            RedirectAttributes redirectAttributes) {
//        ShoppingCart cart = getCart(session);
//        cart.clear();
//        session.setAttribute("cart", cart);
//
//        redirectAttributes.addFlashAttribute("success", "Cart cleared.");
//        return "redirect:/cart";
//    }
//}

package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.ecommerce.service.ProductService;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;

    @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    // Get or create the shopping cart from session
    private ShoppingCart getCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cart", getCart(session));
        return "cart/view";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") int quantity,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }


        if (product.getStock() < quantity) {
            redirectAttributes.addFlashAttribute("error",
                    "Sorry, only " + product.getStock() + " items available in stock.");
            return "redirect:/products/" + productId;
        }

        ShoppingCart cart = getCart(session);
        cart.addItem(product, quantity);
        session.setAttribute("cart", cart);

        redirectAttributes.addFlashAttribute("success",
                product.getName() + " added to your cart successfully!");
        return "redirect:/cart";
    }


    @PostMapping("/update/{productId}")
    public String updateCartItem(
            @PathVariable Long productId,
            @RequestParam int quantity,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        ShoppingCart cart = getCart(session);

        if (quantity <= 0) {
            cart.removeItem(productId);
            redirectAttributes.addFlashAttribute("success", "Item removed.");
        } else {
            Product product = productService.getProductById(productId);
            if (product == null) {
                redirectAttributes.addFlashAttribute("error", "Product not found.");
            } else if (product.getStock() < quantity) {
                redirectAttributes.addFlashAttribute("error",
                        "Only " + product.getStock() + " item(s) available.");
            } else {
                cart.updateItemQuantity(productId, quantity);
                redirectAttributes.addFlashAttribute("success", "Cart updated.");
            }
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeCartItem(
            @PathVariable Long productId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        ShoppingCart cart = getCart(session);
        cart.removeItem(productId);
        session.setAttribute("cart", cart);

        redirectAttributes.addFlashAttribute("success", "Item removed.");
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        ShoppingCart cart = getCart(session);
        cart.clear();
        session.setAttribute("cart", cart);

        redirectAttributes.addFlashAttribute("success", "Cart cleared.");
        return "redirect:/cart";
    }
}
