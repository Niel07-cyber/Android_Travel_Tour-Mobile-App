package com.example.myapplication.managers;

import com.example.myapplication.Domain.ItemDomain;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<ItemDomain> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Add an item to the cart
    public void addToCart(ItemDomain item) {
        cartItems.add(item);
    }

    // Remove a specific item from the cart
    public void removeFromCart(ItemDomain item) {
        cartItems.remove(item); // Remove the item based on equality check
    }

    // Optionally, you could remove based on index if needed
    public void removeFromCart(int index) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
    }

    // Return a copy of the cart items for safety
    public List<ItemDomain> getCartItems() {
        return new ArrayList<>(cartItems);
    }
}
