package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.ItemDomain;
import com.example.myapplication.R;
import com.example.myapplication.managers.CartManager;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final List<ItemDomain> cartItems;

    public CartAdapter(List<ItemDomain> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ItemDomain item = cartItems.get(position);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getTimeTour());
        holder.address.setText(item.getAddress());
        holder.price.setText("Price: $" + item.getPrice()); // Set the price for the item

        Glide.with(holder.itemView.getContext())
                .load(item.getPic())
                .into(holder.imageView);

        // Delete button logic
        holder.deleteButton.setOnClickListener(v -> {
            CartManager.getInstance().removeFromCart(item); // Remove item from CartManager
            cartItems.remove(position); // Remove item from adapter list
            notifyItemRemoved(position); // Notify adapter about item removal
            notifyItemRangeChanged(position, cartItems.size()); // Update item range for correct display
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView title, time, address, price; // Added price to ViewHolder
        ImageView imageView;
        ImageView deleteButton;

        CartViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cartTitleTxt);
            time = itemView.findViewById(R.id.cartTimeTxt);
            address = itemView.findViewById(R.id.cartAddressTxt);
            price = itemView.findViewById(R.id.cartPriceTxt); // Reference to price TextView
            imageView = itemView.findViewById(R.id.cartImageView);
            deleteButton = itemView.findViewById(R.id.deleteButton); // Reference to delete button
        }
    }
}
