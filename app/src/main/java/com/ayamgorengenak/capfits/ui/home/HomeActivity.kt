package com.ayamgorengenak.capfits.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.databinding.ActivityHomeBinding
import com.ayamgorengenak.capfits.ui.recommend.CameraActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnKeranjang: Button = binding.buttonKeranjang
        val btnNotifikasi: Button = binding.buttonNotifikasi
        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.navigation_capture) {
                startActivity(Intent(this@HomeActivity, CameraActivity::class.java))
                navView.visibility = View.GONE
                btnKeranjang.visibility = View.GONE
                btnNotifikasi.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
                toolbar.visibility = View.VISIBLE
            }
        }
        navView.setupWithNavController(navController)
    }

}