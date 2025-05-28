package com.shewants.app.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shewants.app.model.Product
import java.io.IOException

object ProductUtils {

    fun loadProducts(context: Context): List<Product> {
        return try {
            val inputStream = context.assets.open("products.json")
            val json = inputStream.bufferedReader().use { it.readText() }

            val type = object : TypeToken<List<Product>>() {}.type
            Gson().fromJson(json, type)


        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}
