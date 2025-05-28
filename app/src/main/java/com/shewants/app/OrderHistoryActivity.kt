package com.shewants.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.shewants.app.data.AppDatabase
import com.shewants.app.databinding.ActivityOrderHistoryBinding
import com.shewants.app.ui.OrderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderHistoryBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shewants-db"
        ).fallbackToDestructiveMigration().build()

        lifecycleScope.launch(Dispatchers.IO) {
            val orders = database.orderDao().getAll()
            launch(Dispatchers.Main) {
                binding.ordersRecyclerView.layoutManager = LinearLayoutManager(this@OrderHistoryActivity)
                binding.ordersRecyclerView.adapter = OrderAdapter(orders)
            }
        }
    }
}
