package com.ayamgorengenak.capfits.ui.capture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ayamgorengenak.capfits.databinding.FragmentCaptureBinding
import com.ayamgorengenak.capfits.databinding.FragmentDashboardBinding

class CaptureFragment : Fragment() {

    private var _binding: FragmentCaptureBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val captureViewModel =
            ViewModelProvider(this)[CaptureViewModel::class.java]

        _binding = FragmentCaptureBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textCapture
//        captureViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}