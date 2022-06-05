package com.ayamgorengenak.capfits.ui

import android.Manifest
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.databinding.ActivityLoginBinding
import com.ayamgorengenak.capfits.databinding.ActivityResultBinding
import com.ayamgorengenak.capfits.utils.rotateBitmap
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.File

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    private var getFile: File? = null
    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BottomSheetBehavior.from(binding.sheet).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
       registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == CAMERA_X_RESULT) {
                val myFile = it.data?.getSerializableExtra("picture") as File
                val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
                getFile = myFile
                val result = rotateBitmap(
                    BitmapFactory.decodeFile(getFile?.path),
                    isBackCamera
                )
                binding.resultCapture.setImageBitmap(result)
            }
        }
    }
}