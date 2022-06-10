package com.ayamgorengenak.capfits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.WarnaAdapter.WarnaViewHolder
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ItemResultBinding
import com.ayamgorengenak.capfits.databinding.ItemWarnaBinding
import com.ayamgorengenak.capfits.ui.recommend.ListRecommendAdapter


class WarnaAdapter(private val warna: ArrayList<String>?) :
    RecyclerView.Adapter<WarnaAdapter.WarnaViewHolder>() {


    inner class WarnaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvWarna: TextView = itemView.findViewById((R.id.warna))
    }
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(warna: String) {
//            binding.apply {
//                warna
//            }
//        }
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarnaViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_warna, parent, false)
        return WarnaViewHolder(view)

    }

    override fun onBindViewHolder(holder: WarnaViewHolder, position: Int) {
        holder.tvWarna.text = warna!!.get(position)
    }

    override fun getItemCount(): Int = warna?.size!!
}