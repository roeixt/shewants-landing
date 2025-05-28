package com.shewants.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shewants.app.databinding.ItemProductBinding
import com.shewants.app.model.CartItem

class CartAdapter(
    private val cartItems: List<CartItem>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            binding.productName.text = item.name
            binding.productPrice.text = "$%.2f x %d".format(item.price, item.quantity)
            binding.productDescription.text = ""
            binding.buyNowButton.isEnabled = false
            binding.addToCartButton.isEnabled = false
            binding.addToCartButton.text = "Added"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItems.size
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }
}
