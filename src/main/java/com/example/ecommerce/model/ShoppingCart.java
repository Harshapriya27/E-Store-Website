////package com.example.ecommerce.model;
////
////import lombok.Data;
////
////import java.math.BigDecimal;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Optional;
////
////@Data
////public class ShoppingCart {
////
////    private List<CartItem> items = new ArrayList<>();
////
////    public List<CartItem> getItems() {
////        return items;
////    }
////
////    public void setItems(List<CartItem> items) {
////        this.items = items;
////    }
////
////    public void addItem(Product product, int quantity) {
////        Optional<CartItem> existingItem = items.stream()
////                .filter(item -> item.getProductId().equals(product.getId()))
////                .findFirst();
////
////        if (existingItem.isPresent()) {
////            CartItem item = existingItem.get();
////            item.setQuantity(item.getQuantity() + quantity);
////        } else {
////            CartItem newItem = new CartItem(
////                    product.getId(),
////                    product.getName(),
////                    product.getPrice(),
////                    quantity,
////                    product.getImageUrl()
////            );
////            items.add(newItem);
////        }
////    }
////
////    public void updateItemQuantity(Long productId, int quantity) {
////        items.stream()
////                .filter(item -> item.getProductId().equals(productId))
////                .findFirst()
////                .ifPresent(item -> {
////                    if (quantity > 0) {
////                        item.setQuantity(quantity);
////                    } else {
////                        removeItem(productId);
////                    }
////                });
////    }
////
////    public void removeItem(Long productId) {
////        items.removeIf(item -> item.getProductId().equals(productId));
////    }
////
////    public BigDecimal getTotalAmount() {
////        return items.stream()
////                .map(CartItem::getSubtotal)
////                .reduce(BigDecimal.ZERO, BigDecimal::add);
////    }
////
////    public int getItemCount() {
////        return items.stream()
////                .mapToInt(CartItem::getQuantity)
////                .sum();
////    }
////
////    public void clear() {
////        items.clear();
////    }
////}
//
//package com.example.ecommerce.model;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class ShoppingCart {
//    private List<CartItem> items = new ArrayList<>();
//
//    public List<CartItem> getItems() {
//        return items;
//    }
//
//    public void addItem(Product product, int quantity) {
//        Optional<CartItem> existingItem = items.stream()
//                .filter(item -> item.getProductId().equals(product.getId()))
//                .findFirst();
//
//        if (existingItem.isPresent()) {
//            CartItem item = existingItem.get();
//            item.setQuantity(item.getQuantity() + quantity);
//        } else {
//            items.add(new CartItem(
//                    product.getId(),
//                    product.getName(),
//                    product.getPrice(),
//                    quantity,
//                    product.getImageUrl()
//            ));
//        }
//    }
//
//    public void updateItemQuantity(Long productId, int quantity) {
//        items.stream()
//                .filter(item -> item.getProductId().equals(productId))
//                .findFirst()
//                .ifPresent(item -> {
//                    if (quantity > 0) item.setQuantity(quantity);
//                    else items.removeIf(i -> i.getProductId().equals(productId));
//                });
//    }
//
//    public BigDecimal getTotalAmount() {
//        return items.stream()
//                .map(CartItem::getSubtotal)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
//
//    public void clear() {
//        items.clear();
//    }
//
//    public void removeItem(Long productId) {
//        items.removeIf(item -> item.getProductId().equals(productId));
//    }
//}


package com.example.ecommerce.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    // add this
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Product product, int quantity) {
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProductId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.add(new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    quantity,
                    product.getImageUrl()
            ));
        }
    }

    public void updateItemQuantity(Long productId, int quantity) {
        items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    if (quantity > 0) item.setQuantity(quantity);
                    else items.removeIf(i -> i.getProductId().equals(productId));
                });
    }

    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        items.clear();
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
    }
}
