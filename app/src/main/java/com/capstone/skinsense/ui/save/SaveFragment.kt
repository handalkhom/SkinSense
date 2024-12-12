package com.capstone.skinsense.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.skinsense.databinding.FragmentSaveBinding
import com.capstone.skinsense.data.local.AppDatabase
import com.capstone.skinsense.data.local.PredictionResult
import kotlinx.coroutines.launch

class SaveFragment : Fragment() {

    private var _binding: FragmentSaveBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SaveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        adapter = SaveAdapter(mutableListOf())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SaveFragment.adapter
        }   

        // Fetch data from database
        val database = AppDatabase.getInstance(requireContext())
        val dao = database.predictionResultDao()

        dao.getAllResults().observe(viewLifecycleOwner) { results ->
            adapter.updateData(results)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}