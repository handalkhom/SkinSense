package com.capstone.skinsense.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.capstone.skinsense.R
import com.capstone.skinsense.databinding.FragmentHomeBinding
import com.capstone.skinsense.ui.profile.ProfileViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Observe LiveData and update UI
        profileViewModel.username.observe(viewLifecycleOwner) {
            binding.greetingTextView.text = getString(R.string.hallo) + (it ?: getString(R.string.unknown))
        }

        // Navigate to ScanFragment when button is clicked
        binding.tryButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_scan)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
