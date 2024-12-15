package com.capstone.skinsense.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsense.R
import com.capstone.skinsense.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // Observe LiveData and update UI
        profileViewModel.username.observe(viewLifecycleOwner) {
            binding.usernameTextView.text = it ?: getString(R.string.unknown)
        }
        profileViewModel.name.observe(viewLifecycleOwner) {
            binding.nameTextView.text = it ?: getString(R.string.unknown)
        }
        profileViewModel.email.observe(viewLifecycleOwner) {
            binding.emailTextView.text = it ?: getString(R.string.unknown)
        }
        profileViewModel.phone.observe(viewLifecycleOwner) {
            binding.phoneTextView.text = it ?: getString(R.string.unknown)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
