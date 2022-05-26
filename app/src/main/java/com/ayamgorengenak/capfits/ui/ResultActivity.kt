package com.ayamgorengenak.capfits.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.databinding.ActivityLoginBinding
import com.ayamgorengenak.capfits.databinding.ActivityResultBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BottomSheetBehavior.from(binding.sheet).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}