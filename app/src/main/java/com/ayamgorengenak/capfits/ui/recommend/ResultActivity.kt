package com.ayamgorengenak.capfits.ui.recommend

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.backend.ApiConfig.Companion.getApiService
import com.ayamgorengenak.capfits.backend.FileUploadResponse
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ActivityResultBinding
import com.ayamgorengenak.capfits.utils.rotateBitmap
import com.ayamgorengenak.capfits.utils.rotateMedia
import com.google.android.material.bottomsheet.BottomSheetBehavior
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var rvRecommend: RecyclerView

    private var ss: File? = null

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Permission needed",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvRecommend = findViewById(R.id.rv_category)
        rvRecommend.setHasFixedSize(true)

//        if (listRecommend != null) {
//            list.addAll(listRecommend)
//        }
//        showRecyclerList()

        val sheet = findViewById<LinearLayout>(R.id.sheet)
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from<View>(sheet)
        behavior.peekHeight = 1000
        behavior.maxHeight = 1700

//        val story : MutableList<ListRekomendasiItem> =
//        getList(story)
//        setupRecyclerView()
//        BottomSheetBehavior.from(sheet)

//        val listView = findViewById<RecyclerView>(R.id.rv_category)
//        val adapter = ListAdapter()
//        val item = (1..5).map { "item$it" }
//        adapter.setItems(item)

//        listView.adapter = adapter

        supportActionBar?.title = "Add Story"

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        val ss: File = intent.getSerializableExtra("picture") as File
        if (intent.getSerializableExtra("isCamera") == 1) {
            val result = rotateBitmap(
                BitmapFactory.decodeFile(ss.path)
            )
            binding.resultCapture.setImageBitmap(result)
            uploadImage(ss)
        } else {
            val result = rotateMedia(BitmapFactory.decodeFile(ss.path))
            binding.resultCapture.setImageBitmap(result)
            uploadImage(ss)
        }
    }

    private fun getList(rec: MutableList<ListRekomendasiItem>) {
        val storyAdapter = ListRecommendAdapter(rec)
        binding.rvCategory.adapter = storyAdapter
    }

//    private val listRecommend: ArrayList<ListRekomendasiItem>
//        get() {
//            val dataTitle = resources.getStringArray(R.array.data_title)
//            val dataPrice = resources.getStringArray(R.array.data_price)
//            val dataLocation = resources.getStringArray(R.array.data_location)
//            val dataStar = resources.getStringArray(R.array.data_star)
//            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
//
//            val listRecommend = ArrayList<ListRekomendasiItem>()
//            for (i in dataTitle.indices) {
//                val recommend = ListRekomendasiItem(dataTitle[i], dataPrice[i].toInt(), dataLocation[i], dataStar[i].toInt(), dataPhoto.getResourceId(i, -1).toString())
//                listRecommend.add(recommend)
//            }
//            return listRecommend
//        }

    private fun showRecyclerList(rec: MutableList<ListRekomendasiItem>) {
        rvRecommend.layoutManager = GridLayoutManager(this, 2)
        val listRecommendAdapter = ListRecommendAdapter(rec)
        rvRecommend.adapter = listRecommendAdapter
        listRecommendAdapter.setOnClickCallBack(object : ListRecommendAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListRekomendasiItem) {
                val service = getApiService().detail(id)

                service.enqueue(object : Callback<FileUploadResponse> {
                    override fun onResponse(
                        call: Call<FileUploadResponse>,
                        response: Response<FileUploadResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.e("cek", "bisaqa")
//                            Intent(this@ResultActivity, MainActivity::class.java).also {
//                                finish()
//                            }
                            val responseBody = response.body()
                            if (responseBody != null && !responseBody.error) {
                                showRecyclerList(responseBody.data)
                                Toast.makeText(
                                    this@ResultActivity,
                                    responseBody.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@ResultActivity,
                                response.message(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<FileUploadResponse>, t: Throwable) {
                        Log.e("cek", "thidaa")
                        Toast.makeText(
                            this@ResultActivity,
                            "Cannot instance Retrofit",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

                Intent(this@ResultActivity, DetailProductActivity::class.java).also {
                    it.putExtra(DetailProductActivity.EXTRA_DATA, data)
                    startActivity(it)

                }

            }
        })
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvCategory.layoutManager = layoutManager
        binding.rvCategory.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvCategory.addItemDecoration(itemDecoration)
    }

    private fun uploadImage(ss: File) {
//        Log.e("cek", "$ss")
        if (ss != null) {
            val bitimg = BitmapFactory.decodeFile(ss.path)
            val byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
            bitimg.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteimg = byteArrayOutputStream.toByteArray()
            val encodedimg = android.util.Base64.encodeToString(byteimg, Base64.DEFAULT)

            val service = getApiService().uploadImage(encodedimg)

            service.enqueue(object : Callback<FileUploadResponse> {
                override fun onResponse(
                    call: Call<FileUploadResponse>,
                    response: Response<FileUploadResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("cek", "bisaqa")
//                            Intent(this@ResultActivity, MainActivity::class.java).also {
//                                finish()
//                            }
                        val responseBody = response.body()
                        if (responseBody != null && !responseBody.error) {
                            showRecyclerList(responseBody.data)
                            Toast.makeText(
                                this@ResultActivity,
                                responseBody.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@ResultActivity,
                            response.message(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<FileUploadResponse>, t: Throwable) {
                    Log.e("cek", "thidaa")
                    Toast.makeText(
                        this@ResultActivity,
                        "Cannot instance Retrofit",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } else {
            Toast.makeText(
                this@ResultActivity,
                "Input file first.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
