package com.ayamgorengenak.capfits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.ayamgorengenak.capfits.databinding.ActivityNyobaGambarBinding
import com.ayamgorengenak.capfits.databinding.ActivityResultBinding
import com.bumptech.glide.Glide

private lateinit var binding : ActivityNyobaGambarBinding
class NyobaGambar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNyobaGambarBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val media = "https://storage.googleapis.com/bangkit-capfits.appspot.com/Allbaseimage/1.jpg?Expires=1680528461&GoogleAccessId=firebase-adminsdk-ir8od%40bangkit-capfits.iam.gserviceaccount.com&Signature=fdI9ZwFB8gc0a2mRjbxjshuclGX%2Bpa6zUvmTvQgV9fJUw0E%2FQbTy4%2F%2FK4RQ%2B2Z97Hz8L1mTlb7She5%2BSRonaRmj2jfwYmpVi8YhGgMoHu4qEfWJHXNbN1RNBeDg5UE21qS0cnm%2B0jBYc3DPahsR%2FdBvB%2BilG9EulzfSoZIFCaZx6VvsFkJqUBqC4k9H445sAwVPxGMQFdY%2BzWFNY%2FXNLJLU1BzeXUwpuYA%2Faop4DRBl%2BiPyKCLZNOw4YE8DBmaz%2B0vdh%2FAJJfc9v5V0Iwdei2DEb9%2BOStWGFR46Qd4xLHaPKS73JrGOzUNlQttTEdJ0I1EdK%2Bdzj70i2nIezpHNQ3g%3D%3D"
        Glide.with(this)
            .load(media)
            .into(binding.imageView)
    }
}