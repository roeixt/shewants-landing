package com.shewants.app.ui

import com.shewants.app.model.Product

object CategoryFilter {
    fun filterByCategory(products: List<Product>, category: String): List<Product> {
        return products.filter { it.category.equals(category, ignoreCase = true) }
    }

    fun getUniqueCategories(products: List<Product>): List<String> {
        return products.map { it.category }.distinct().sorted()
    }
}
