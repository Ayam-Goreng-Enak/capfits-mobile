package com.ayamgorengenak.capfits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayamgorengenak.capfits.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDaftar.setOnClickListener {
            Intent(this@LoginActivity, DaftarActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.buttonLogin.setOnClickListener {
            Intent(this@LoginActivity, HomeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}