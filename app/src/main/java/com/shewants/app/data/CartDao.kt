package com.shewants.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shewants.app.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert
    suspend fun insert(item: CartItem)

    @Query("SELECT * FROM cart")
    fun getAll(): Flow<List<CartItem>>  // Changed to Flow

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}