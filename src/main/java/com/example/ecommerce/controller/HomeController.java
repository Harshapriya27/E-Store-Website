//package com.example.ecommerce.controller;
//
//import com.example.ecommerce.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class HomeController {
//
//    private final ProductService productService;
//
//    @Autowired
//    public HomeController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("products", productService.getAllProducts());
//        return "index";
//    }
//}

package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping("/")
//    public String home(Model model) {
//        List<Product> featuredProducts = productService.getFeaturedProducts();
//        model.addAttribute("featuredProducts", featuredProducts);
//        model.addAttribute("totalProducts", productService.getTotalProductCount());
//        return "index";
//    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        return "index";
    }


}