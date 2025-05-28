package com.shewants.app.model

data class Product(
    val id: Int,
    val category: String,
    val name: String,
    val originalCost: Double,
    val price: Double,
    val estimatedProfit: Double,
    val image: String,
    val link: String,
    val description: String
)
