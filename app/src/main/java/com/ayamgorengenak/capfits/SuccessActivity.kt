package com.ayamgorengenak.capfits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayamgorengenak.capfits.databinding.ActivityDetailProductBinding
import com.ayamgorengenak.capfits.databinding.ActivitySuccessBinding
import com.ayamgorengenak.capfits.ui.home.HomeActivity

private lateinit var binding: ActivitySuccessBinding
class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHome.setOnClickListener {
            Intent(this@SuccessActivity, HomeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}