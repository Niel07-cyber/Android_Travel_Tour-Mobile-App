package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Adapter.CartAdapter;
import com.example.myapplication.Domain.ItemDomain;
import com.example.myapplication.databinding.ActivityCartBinding;
import com.example.myapplication.managers.CartManager;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get cart items
        List<ItemDomain> cartItems = CartManager.getInstance().getCartItems();

        // Set up RecyclerView
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        CartAdapter cartAdapter = new CartAdapter(cartItems);
        binding.recyclerViewCart.setAdapter(cartAdapter);

        // Set up Checkout button click listener
        binding.checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start LoginActivity
                Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
