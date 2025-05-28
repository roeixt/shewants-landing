package com.shewants.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.shewants.app.data.AppDatabase
import com.shewants.app.databinding.ActivityCartBinding
import com.shewants.app.model.Order
import com.shewants.app.ui.CartAdapter
import com.shewants.app.utils.ShippingPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shewants-db"
        ).fallbackToDestructiveMigration().build()

        lifecycleScope.launch(Dispatchers.IO) {
            // 🧠 getAll returns Flow<List<CartItem>> – use .first() to collect
            val items = database.cartDao().getAll().first()
            val total = items.sumOf { it.price * it.quantity }

            launch(Dispatchers.Main) {
                binding.cartRecyclerView.layoutManager = LinearLayoutManager(this@CartActivity)
                binding.cartRecyclerView.adapter = CartAdapter(items)
                binding.totalPriceText.text = "Total: $%.2f".format(total)
            }
        }

        binding.checkoutButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val items = database.cartDao().getAll().first()
                val total = items.sumOf { it.price * it.quantity }
                val (name, city, phone) = ShippingPrefs.get(this@CartActivity)

                database.orderDao().insert(
                    Order(
                        customerName = name,
                        city = city,
                        phone = phone,
                        totalPrice = total,
                        timestamp = System.currentTimeMillis()
                    )
                )

                database.cartDao().clearCart()
                delay(1000)

                launch(Dispatchers.Main) {
                    val intent = Intent(this@CartActivity, ThankYouActivity::class.java)
                    intent.putExtra("name", name)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
