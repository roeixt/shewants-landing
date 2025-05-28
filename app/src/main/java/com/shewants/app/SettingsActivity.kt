package com.shewants.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shewants.app.databinding.ActivitySettingsBinding
import com.shewants.app.utils.ShippingPrefs

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (name, city, phone) = ShippingPrefs.get(this)
        binding.nameEditText.setText(name)
        binding.cityEditText.setText(city)
        binding.phoneEditText.setText(phone)

        binding.saveButton.setOnClickListener {
            ShippingPrefs.save(
                this,
                binding.nameEditText.text.toString(),
                binding.cityEditText.text.toString(),
                binding.phoneEditText.text.toString()
            )
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
