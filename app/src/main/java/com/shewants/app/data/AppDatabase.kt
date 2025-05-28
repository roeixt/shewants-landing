package com.shewants.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shewants.app.model.CartItem
import com.shewants.app.model.Order

@Database(entities = [CartItem::class, Order::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
}
