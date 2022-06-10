package com.ayamgorengenak.capfits.ui.recommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.backend.ApiConfig
import com.ayamgorengenak.capfits.backend.FileDetailResponse
import com.ayamgorengenak.capfits.backend.ListDetailItem
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ActivityDetailProductBinding
import com.ayamgorengenak.capfits.databinding.ItemWarnaBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val id = intent.getIntExtra("id", -1)
        val service = ApiConfig.getApiService().detail(id)

        service.enqueue(object : Callback<FileDetailResponse> {
            override fun onResponse(
                call: Call<FileDetailResponse>,
                response: Response<FileDetailResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("cek", "bisaqa")
//                            Intent(this@ResultActivity, MainActivity::class.java).also {
//                                finish()
//                            }
                    val responseBody = response.body()


                } else {
                    Toast.makeText(
                        this@DetailProductActivity,
                        response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<FileDetailResponse>, t: Throwable) {
                Log.e("cek", "thidaa")
                Toast.makeText(
                    this@DetailProductActivity,
                    "Cannot instance Retrofit",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val detail = intent.getParcelableExtra<ListDetailItem>(EXTRA_DATA)
        val image = detail?.foto
        val judul = detail?.nama_outfit
        val sewa = detail?.harga_sewa
        val lokasi = detail?.lokasi
        val rating = detail?.rating
        val warna = detail?.rating
        val deskripsi = detail?.deskripsi
        val detailProduct = detail?.detailProduk
        val nama = detail?.nama






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