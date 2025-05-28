package com.shewants.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerName: String,
    val city: String,
    val phone: String,
    val totalPrice: Double,
    val timestamp: Long
)
