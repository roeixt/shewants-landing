package com.shewants.app.utils

import android.content.Context

object ShippingPrefs {
    private const val PREFS = "shipping_prefs"

    fun save(context: Context, name: String, city: String, phone: String) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("name", name)
            putString("city", city)
            putString("phone", phone)
            apply()
        }
    }

    fun get(context: Context): Triple<String, String, String> {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return Triple(
            prefs.getString("name", "") ?: "",
            prefs.getString("city", "") ?: "",
            prefs.getString("phone", "") ?: ""
        )
    }
}
