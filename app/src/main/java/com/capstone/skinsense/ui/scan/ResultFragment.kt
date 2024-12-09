package com.capstone.skinsense.ui.scan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.capstone.skinsense.R
import com.capstone.skinsense.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let {
            ResultFragmentArgs.fromBundle(it)
        }

        // Set preview image
        val imageUri = args?.imageUri?.toUri()
        binding.previewImageView.setImageURI(imageUri)

        // Set prediction result
        val resultText = args?.resultText
        binding.resultTextView.text = resultText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
