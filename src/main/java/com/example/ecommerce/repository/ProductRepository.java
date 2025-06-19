////package com.example.ecommerce.repository;
////
////import com.example.ecommerce.model.Product;
////import org.springframework.data.jpa.repository.JpaRepository;
////import org.springframework.stereotype.Repository;
////
////@Repository
////public interface ProductRepository extends JpaRepository<Product, Long> {
////    // Spring Data JPA will automatically implement basic CRUD operations
////}
//
//package com.example.ecommerce.repository;
//
//import com.example.ecommerce.model.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findByFeaturedTrue();
//}

package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByFeaturedTrue();

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> findAvailableProducts();

    @Query("SELECT p FROM Product p WHERE p.featured = true AND p.stock > 0")
    List<Product> findFeaturedAvailableProducts();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.stock > 0")
    long countAvailableProducts();
}