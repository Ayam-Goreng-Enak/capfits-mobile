package com.ayamgorengenak.capfits.ui.capture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ayamgorengenak.capfits.databinding.FragmentCaptureBinding
import com.google.android.material.appbar.AppBarLayout


class CaptureFragment : Fragment() {
    private var _binding: FragmentCaptureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        AppBarLayout.GONE
        val captureViewModel =
            ViewModelProvider(this)[CaptureViewModel::class.java]
        _binding = FragmentCaptureBinding.inflate(inflater, container, false)

        return binding.root
    }

//    override fun onResume() {
//        super.onResume()
//        (activity as CaptureFragment?)!!.supportActionBar!!.hide()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        (activity as CaptureFragment?)!!.supportActionBar!!.show()
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}