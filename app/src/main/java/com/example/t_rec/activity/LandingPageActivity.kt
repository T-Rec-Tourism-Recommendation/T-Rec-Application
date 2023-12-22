package com.example.t_rec.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.t_rec.databinding.ActivityLandingPageBinding

class LandingPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLanding.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}