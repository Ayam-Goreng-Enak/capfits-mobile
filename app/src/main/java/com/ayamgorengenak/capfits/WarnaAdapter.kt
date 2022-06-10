package com.ayamgorengenak.capfits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.WarnaAdapter.WarnaViewHolder
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ItemResultBinding
import com.ayamgorengenak.capfits.ui.recommend.ListRecommendAdapter


class WarnaAdapter (private val warna: ArrayList<String>) :
    RecyclerView.Adapter<WarnaViewHolder>() {


    inner class WarnaViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(warna: String) {
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarnaViewHolder {
        val view = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WarnaViewHolder((view))
    }

    override fun onBindViewHolder(holder: WarnaViewHolder, position: Int) {
        holder.bind(warna[position])
    }

    override fun getItemCount(): Int = warna.size

}