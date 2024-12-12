package com.capstone.skinsense.ui.save

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.skinsense.databinding.ItemPredictionResultBinding
import com.capstone.skinsense.data.local.PredictionResult

class SaveAdapter(private val results: MutableList<PredictionResult>) :
    RecyclerView.Adapter<SaveAdapter.SaveViewHolder>() {

    inner class SaveViewHolder(private val binding: ItemPredictionResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: PredictionResult) {
            binding.imagePreview.setImageURI(Uri.parse(result.imageUri))
            binding.resultTextView.text = result.result
            binding.suggestionTextView.text = result.suggestion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding = ItemPredictionResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SaveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size

    fun updateData(newResults: List<PredictionResult>) {
        results.clear()
        results.addAll(newResults)
        notifyDataSetChanged()
    }
}