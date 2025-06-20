//package com.example.ecommerce.config;
//
//import com.example.ecommerce.model.Product;
//import com.example.ecommerce.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public DataInitializer(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        // Check if products already exist
//        if (productRepository.count() == 0) {
//            // Create sample products
//            Product product1 = new Product();
//            product1.setName("Smartphone X");
//            product1.setDescription("Latest smartphone with high-end specifications.");
//            product1.setPrice(new BigDecimal("599.99"));
//            product1.setImageUrl("/images/smartphone.jpg");
//            product1.setStock(50);
//
//            Product product2 = new Product();
//            product2.setName("Laptop Pro");
//            product2.setDescription("Professional laptop for developers and designers.");
//            product2.setPrice(new BigDecimal("1299.99"));
//            product2.setImageUrl("/images/laptop.jpg");
//            product2.setStock(25);
//
//            Product product3 = new Product();
//            product3.setName("Wireless Headphones");
//            product3.setDescription("Premium wireless headphones with noise cancellation.");
//            product3.setPrice(new BigDecimal("199.99"));
//            product3.setImageUrl("/images/headphones.jpg");
//            product3.setStock(100);
//
//            Product product4 = new Product();
//            product4.setName("Smart Watch");
//            product4.setDescription("Smart watch with health monitoring features.");
//            product4.setPrice(new BigDecimal("249.99"));
//            product4.setImageUrl("/images/smartwatch.jpg");
//            product4.setStock(40);
//
//            Product product5 = new Product();
//            product5.setName("Wireless Mouse");
//            product5.setDescription("Ergonomic wireless mouse for productivity.");
//            product5.setPrice(new BigDecimal("49.99"));
//            product5.setImageUrl("/images/mouse.jpg");
//            product5.setStock(150);
//
//            // Save all products
//            productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));
//
//            System.out.println("Sample products have been created.");
//        }
//    }
//}

package com.example.ecommerce.config;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            // Create sample products
            productRepository.save(new Product("Laptop", "High-performance laptop for work and gaming",
                    new BigDecimal("999.99"), "https://cdn.tgdd.vn/Files/2022/07/24/1450033/laptop-man-hinh-full-hd-la-gi-kinh-nghiem-chon-mu-9.jpg", true, 10));
            productRepository.save(new Product("Smartphone", "Latest smartphone with advanced features",
                    new BigDecimal("699.99"), "https://cdn.pixabay.com/photo/2017/02/03/12/46/whatsapp-2035059_960_720.jpg", true, 25));
            productRepository.save(new Product("Headphones", "Wireless noise-cancelling headphones",
                    new BigDecimal("299.99"), "https://th.bing.com/th/id/OIP.NNl3-3u5syrfJ4qrp9U53gHaDt?r=0&rs=1&pid=ImgDetMain&cb=idpwebp2&o=7&rm=3", true, 15));
            productRepository.save(new Product("Tablet", "10-inch tablet for entertainment and productivity",
                    new BigDecimal("399.99"), "https://images.pexels.com/photos/1334597/pexels-photo-1334597.jpeg?cs=srgb&dl=table-technology-ipad-1334597.jpg&fm=jpg", false, 20));
            productRepository.save(new Product("Smart Watch", "Fitness tracking smartwatch",
                    new BigDecimal("249.99"), "https://m.media-amazon.com/images/I/71KjTSO8M9L._SL1500_.jpg", true, 30));
        }

        // Create default admin if not exists
        if (!userRepository.existsByEmail("admin@estore.com")) {
            User admin = new User("admin@estore.com", "admin123", "Admin", "User", "ADMIN");
            userRepository.save(admin);
        }
    }
}