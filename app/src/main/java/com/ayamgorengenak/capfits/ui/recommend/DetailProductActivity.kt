package com.ayamgorengenak.capfits.ui.recommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.SuccessActivity
import com.ayamgorengenak.capfits.UkuranAdapter
import com.ayamgorengenak.capfits.WarnaAdapter
import com.ayamgorengenak.capfits.backend.ApiConfig
import com.ayamgorengenak.capfits.backend.FileDetailResponse
import com.ayamgorengenak.capfits.databinding.ActivityDetailProductBinding
import com.ayamgorengenak.capfits.ui.auth.LoginActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductActivity : AppCompatActivity() {
    private var idOutfit: Int? = null
    private var image: String? = null
    private var judul: String? = null
    private var sewa: Int? = null
    private var lokasi: String? = null
    private var rating: Int? = null
    private var warna: ArrayList<String>? = null
    private var deskripsi: String? = null
    private var detailProduct: String? = null
    private var nama: String? = null
    private var size: ArrayList<String>? = null
    private var hip: ArrayList<String>? = null
    private var waist: ArrayList<String>? = null
    private var length: ArrayList<String>? = null


    private lateinit var rvWarna: RecyclerView
    private lateinit var rvUkuran: RecyclerView
    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)

        binding.buttonSewa.setOnClickListener {
            Intent(this@DetailProductActivity, SuccessActivity::class.java).also {
                startActivity(it)
            }
            binding.backDetail.setOnClickListener {
                Intent(this@DetailProductActivity, ResultActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

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
                    val responseBody = response.body()
                    var itemColor: ArrayList<String> = ArrayList()
                    var tmpColor: String = String()
                    var itemSize: ArrayList<String> = ArrayList()
                    var itemHip: ArrayList<String> = ArrayList()
                    var itemWaist: ArrayList<String> = ArrayList()
                    var itemLength: ArrayList<String> = ArrayList()

                    for (outfit in responseBody?.data!!) {
                        idOutfit = outfit.id_outfit
                        image = outfit.foto
                        judul = outfit.nama_outfit
                        sewa = outfit.harga_sewa
                        lokasi = outfit.lokasi
                        rating = outfit.rating
                        tmpColor = outfit.warna
                        deskripsi = outfit.deskripsi
                        detailProduct = outfit.detailProduk
                        nama = outfit.nama
                        itemSize.add(outfit.size)
                        itemHip.add(outfit.hip)
                        itemWaist.add(outfit.waist)
                        itemLength.add(outfit.length)
                    }
                    for (color in tmpColor.split(",")) {
                        itemColor.add(color)
                    }

                    setWarna(itemColor)
                    setSize(itemSize)
                    setHip(itemHip)
                    setWaist(itemWaist)
                    setLength(itemLength)

                    showRecyclerWarna(itemColor)
                    showRecyclerUkuran(itemSize)


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

        rvWarna = findViewById(R.id.rvWarna)
        rvWarna.setHasFixedSize(true)
        rvUkuran = findViewById(R.id.rvUkuran)
        rvUkuran.setHasFixedSize(true)
    }

    private fun showRecyclerWarna(itemColor: ArrayList<String>) {
        rvWarna.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val warnaAdapter = WarnaAdapter(itemColor)
        for (color in itemColor!!) {
            Log.e("", "$color")
        }
        rvWarna.adapter = warnaAdapter
    }

    private fun showRecyclerUkuran(itemUkuran: ArrayList<String>) {
        rvUkuran.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val ukuranAdapter = UkuranAdapter(itemUkuran)
        for (ukuran in itemUkuran!!) {
            Log.e("", "$ukuran")
        }
        rvUkuran.adapter = ukuranAdapter
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private fun setWarna(warna: ArrayList<String>) {
        this.warna = warna
    }

    private fun setSize(size: ArrayList<String>) {
        this.size = size
    }

    private fun setHip(hip: ArrayList<String>) {
        this.hip = hip
    }

    private fun setWaist(waist: ArrayList<String>) {
        this.waist = waist
    }

    private fun setLength(length: ArrayList<String>) {
        this.length = length
    }
}