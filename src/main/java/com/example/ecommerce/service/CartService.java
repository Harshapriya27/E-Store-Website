package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartService {

    private final Map<String, List<CartItem>> cartStorage = new HashMap<>();

    public List<CartItem> getCartItems(String sessionId) {
        return cartStorage.getOrDefault(sessionId, new ArrayList<>());
    }

    public void addToCart(String sessionId, CartItem item) {
        List<CartItem> cartItems = cartStorage.computeIfAbsent(sessionId, k -> new ArrayList<>());

        Optional<CartItem> existingItem = cartItems.stream()
                .filter(ci -> ci.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem existing = existingItem.get();
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
        } else {
            cartItems.add(item);
        }
    }

    public void updateQuantity(String sessionId, Long productId, int quantity) {
        List<CartItem> cartItems = cartStorage.get(sessionId);
        if (cartItems != null) {
            cartItems.stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst()
                    .ifPresent(item -> item.setQuantity(quantity));
        }
    }

    public void removeItem(String sessionId, Long productId) {
        List<CartItem> cartItems = cartStorage.get(sessionId);
        if (cartItems != null) {
            cartItems.removeIf(item -> item.getProductId().equals(productId));
        }
    }

    public void clearCart(String sessionId) {
        cartStorage.remove(sessionId);
    }

    public BigDecimal getTotal(String sessionId) {
        List<CartItem> cartItems = getCartItems(sessionId);
        return cartItems.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
