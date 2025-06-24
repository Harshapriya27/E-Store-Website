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
            // Sample Products
            productRepository.save(new Product(
                    "Laptop",
                    "A high-performance laptop with Intel i7 processor, 16GB RAM, 512GB SSD – ideal for professionals and gamers.",
                    new BigDecimal("999.99"),
                    "https://cdn.tgdd.vn/Files/2022/07/24/1450033/laptop-man-hinh-full-hd-la-gi-kinh-nghiem-chon-mu-9.jpg",
                    true, 10,
                    true, true,
                    7, 5
            ));

            productRepository.save(new Product(
                    "Smartphone",
                    "Flagship smartphone with AMOLED display, 108MP camera, and fast charging. Android 13 pre-installed.",
                    new BigDecimal("699.99"),
                    "https://cdn.pixabay.com/photo/2017/02/03/12/46/whatsapp-2035059_960_720.jpg",
                    true, 25,
                    true, false,
                    10, 0
            ));

            productRepository.save(new Product(
                    "Wireless Headphones",
                    "Bluetooth-enabled over-ear headphones with active noise cancellation, 30-hour battery life, and ergonomic design.",
                    new BigDecimal("299.99"),
                    "https://th.bing.com/th/id/OIP.NNl3-3u5syrfJ4qrp9U53gHaDt?pid=ImgDetMain",
                    true, 15,
                    false, true,
                    0, 3
            ));

            productRepository.save(new Product(
                    "Tablet",
                    "Sleek 10-inch tablet with stylus support, 128GB storage, and multitasking features for students and creatives.",
                    new BigDecimal("399.99"),
                    "https://images.pexels.com/photos/1334597/pexels-photo-1334597.jpeg",
                    false, 20,
                    true, true,
                    5, 5
            ));

            productRepository.save(new Product(
                    "Smart Watch",
                    "Fitness smartwatch with heart-rate monitor, sleep tracker, Bluetooth calls, and 5-day battery life.",
                    new BigDecimal("249.99"),
                    "https://m.media-amazon.com/images/I/71KjTSO8M9L._SL1500_.jpg",
                    true, 30,
                    false, false,
                    0, 0
            ));
        }

        // Default admin user
        if (!userRepository.existsByEmail("admin@estore.com")) {
            User admin = new User("admin@estore.com", "admin123", "Admin", "User", "ADMIN");
            userRepository.save(admin);
        }
    }
}
