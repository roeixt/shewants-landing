package com.shewants.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shewants.app.databinding.ActivityThankYouBinding

class ThankYouActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThankYouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name") ?: "there"
        binding.thankYouText.text = "Thank you for your order, $name!"
    }
}
