package com.ayamgorengenak.capfits.ui.recommend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ActivityDetailProductBinding
import com.ayamgorengenak.capfits.databinding.ItemWarnaBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var binding2: ItemWarnaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val detail = intent.getParcelableExtra<ListRekomendasiItem>(EXTRA_DATA)
        val image = detail?.foto
        val judul = detail?.nama_outfit
        val sewa = detail?.harga_sewa
        val lokasi = detail?.lokasi
        val rating = detail?.rating
        val warna = detail?.rating




//        binding2.warna.text = detail?.warna.toString()


        binding.apply {

            Glide.with(this@DetailProductActivity)
                .load(image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(fotoDetail)
            titleProduct.text = judul
            priceProduct.text = sewa.toString()
            locationProduct.text = lokasi
            starProduct.text = rating.toString()
            descriptionProduct.text = deskripsi
            penjual.text = nama
            lokasiPenjual.text = lokasi
            detailproduct.text = detailProduct


        }

    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}