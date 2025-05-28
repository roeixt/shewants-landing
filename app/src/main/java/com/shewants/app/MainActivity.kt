package com.shewants.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.shewants.app.data.AppDatabase
import com.shewants.app.databinding.ActivityMainBinding
import com.shewants.app.model.CartItem
import com.shewants.app.model.Product
import com.shewants.app.ui.ProductAdapter
import com.shewants.app.utils.ProductUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    private lateinit var productList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shewants-db"
        ).fallbackToDestructiveMigration().build()

        productList = ProductUtils.loadProducts(this)

        val adapter = ProductAdapter(productList) { product ->
            lifecycleScope.launch(Dispatchers.IO) {
                database.cartDao().insert(
                    CartItem(
                        productId = product.id,
                        name = product.name,
                        price = product.price,
                        quantity = 1
                    )
                )
                launch(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Added to cart", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.productsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.productsRecyclerView.adapter = adapter

        binding.cartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        binding.settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.orderHistoryButton.setOnClickListener {
            startActivity(Intent(this, OrderHistoryActivity::class.java))
        }
    }
}
