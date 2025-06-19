//package com.example.ecommerce.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CartItem {
//
//    private Long productId;
//    private String productName;
//    private BigDecimal productPrice;
//    private int quantity;
//    private String imageUrl;
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public BigDecimal getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(BigDecimal productPrice) {
//        this.productPrice = productPrice;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public BigDecimal getSubtotal() {
//        return productPrice.multiply(new BigDecimal(quantity));
//    }
//}

package com.example.ecommerce.model;

import java.math.BigDecimal;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;
    private String imageUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CartItem(Long productId, String productName,
                    BigDecimal productPrice, int quantity, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public BigDecimal getSubtotal() {
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
