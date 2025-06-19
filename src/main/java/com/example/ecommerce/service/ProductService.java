////package com.example.ecommerce.service;
////
////import com.example.ecommerce.model.Product;
////import com.example.ecommerce.repository.ProductRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////import java.util.Optional;
////
////@Service
////public class ProductService {
////
////    private final ProductRepository productRepository;
////
////    @Autowired
////    public ProductService(ProductRepository productRepository) {
////        this.productRepository = productRepository;
////    }
////
////    public List<Product> getAllProducts() {
////        return productRepository.findAll();
////    }
////
////    public Optional<Product> getProductById(Long id) {
////        return productRepository.findById(id);
////    }
////
////    public Product saveProduct(Product product) {
////        return productRepository.save(product);
////    }
////
////    public void deleteProduct(Long id) {
////        productRepository.deleteById(id);
////    }
////}
//
//package com.example.ecommerce.service;
//
//import com.example.ecommerce.model.Product;
//import com.example.ecommerce.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductService {
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public List<Product> getFeaturedProducts() {
//        return productRepository.findByFeaturedTrue();
//    }
//
//    public long getTotalProductCount() {
//        return productRepository.count();
//    }
//
//    public Product getProductById(Long id) {
//        return productRepository.findById(id).orElse(null);
//    }
//}

package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getFeaturedProducts() {
        return productRepository.findByFeaturedTrue();
    }

    public long getTotalProductCount() {
        return productRepository.count();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}