package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAvailableProducts(); // ✅ Show only available
        model.addAttribute("products", products);
        model.addAttribute("pageTitle", "All Products");
        return "products/list";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("pageTitle", product.getName());

        // ✅ Add return/exchange info for display
        model.addAttribute("returnable", product.isReturnable());
        model.addAttribute("exchangeable", product.isExchangeable());
        model.addAttribute("returnDays", product.getReturnWithinDays());
        model.addAttribute("exchangeDays", product.getExchangeWithinDays());

        return "products/detail";
    }
}
