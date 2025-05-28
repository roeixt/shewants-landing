package com.shewants.app.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shewants.app.databinding.ItemProductBinding
import com.shewants.app.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val products: List<Product>,
    private val onAddToCart: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productPrice.text = "$%.2f".format(product.price)
            binding.productDescription.text = product.description
            Picasso.get().load(product.image).into(binding.productImage)

            binding.addToCartButton.setOnClickListener {
                onAddToCart(product)
            }

            binding.buyNowButton.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Redirecting to AliExpress. You may need to log in to complete your purchase.",
                    Toast.LENGTH_LONG
                ).show()

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link))
                binding.root.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
}
