package com.shewants.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shewants.app.model.Order

@Dao
interface OrderDao {
    @Insert
    suspend fun insert(order: Order)

    @Query("SELECT * FROM orders ORDER BY timestamp DESC")
    suspend fun getAll(): List<Order>
}
