package com.ayamgorengenak.capfits.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.backend.ApiConfig.Companion.getApiService
import com.ayamgorengenak.capfits.backend.FileUploadResponse
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ActivityResultBinding
import com.ayamgorengenak.capfits.utils.reduceFileImage
import com.ayamgorengenak.capfits.utils.rotateBitmap
import com.google.android.material.bottomsheet.BottomSheetBehavior
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

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

//        val story : MutableList<ListRekomendasiItem> =
        val sheet = findViewById<LinearLayout>(R.id.sheet)
        setContentView(binding.root)
//        getList(story)
        setupRecyclerView()
//        BottomSheetBehavior.from(sheet)

        val listView = findViewById<RecyclerView>(R.id.rv_category)
        val adapter = ListAdapter()
        val item = (1..5).map { "item$it" }
        adapter.setItems(item)

        listView.adapter = adapter

        supportActionBar?.title = "Add Story"

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        val ss: File = intent.getSerializableExtra("picture") as File
        val result = rotateBitmap(
            BitmapFactory.decodeFile(ss.path)
        )
        binding.resultCapture.setImageBitmap(result)
        uploadImage(ss)
    }

//    private fun getList(story: MutableList<ListRekomendasiItem>) {
//        val storyAdapter = ListAdapter(story)
//        binding.rvCategory.adapter = storyAdapter
//    }
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvCategory.layoutManager = layoutManager
        binding.rvCategory.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvCategory.addItemDecoration(itemDecoration)
    }

    private fun uploadImage(ss: File) {
        Log.e("cek", "$ss")
        if (ss != null) {
            val file = reduceFileImage(ss as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )

            val service = getApiService().uploadImage(imageMultipart)

            service.enqueue(object : Callback<FileUploadResponse> {
                override fun onResponse(
                    call: Call<FileUploadResponse>,
                    response: Response<FileUploadResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.e("cek", "aaaaaaaaaa")
//                            Intent(this@ResultActivity, MainActivity::class.java).also {
//                                finish()
//                            }
                        val responseBody = response.body()
                        if (responseBody != null && !responseBody.error) {
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
                    Log.e("cek", "aaaasdasdsdaaaaa")
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
