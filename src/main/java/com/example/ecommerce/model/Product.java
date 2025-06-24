package com.example.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl;
    private boolean featured = false;
    private Integer stock = 0;

    // ✅ New fields for return and exchange
    private boolean returnable = false;
    private boolean exchangeable = false;

    private Integer returnWithinDays = 7;     // Optional: number of days
    private Integer exchangeWithinDays = 7;   // Optional: number of days

    // Constructors
    public Product(String name, String description, BigDecimal price, String imageUrl,
                   boolean featured, int stock,
                   boolean returnable, boolean exchangeable,
                   int returnWithinDays, int exchangeWithinDays) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.featured = featured;
        this.stock = stock;
        this.returnable = returnable;
        this.exchangeable = exchangeable;
        this.returnWithinDays = returnWithinDays;
        this.exchangeWithinDays = exchangeWithinDays;
    }

    public Product() {

    }


    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isFeatured() { return featured; }
    public void setFeatured(boolean featured) { this.featured = featured; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public boolean isReturnable() { return returnable; }
    public void setReturnable(boolean returnable) { this.returnable = returnable; }

    public boolean isExchangeable() { return exchangeable; }
    public void setExchangeable(boolean exchangeable) { this.exchangeable = exchangeable; }

    public Integer getReturnWithinDays() { return returnWithinDays; }
    public void setReturnWithinDays(Integer returnWithinDays) { this.returnWithinDays = returnWithinDays; }

    public Integer getExchangeWithinDays() { return exchangeWithinDays; }
    public void setExchangeWithinDays(Integer exchangeWithinDays) { this.exchangeWithinDays = exchangeWithinDays; }
}
