package com.ayamgorengenak.capfits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UkuranAdapter (private val ukuran: ArrayList<String>?) :
    RecyclerView.Adapter<UkuranAdapter.UkuranViewHolder>() {


    inner class UkuranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUkuran: TextView = itemView.findViewById((R.id.ukuranSize))
    }
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(warna: String) {
//            binding.apply {
//                warna
//            }
//        }
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UkuranViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_ukuran, parent, false)
        return UkuranViewHolder(view)

    }

    override fun onBindViewHolder(holder: UkuranViewHolder, position: Int) {
        holder.tvUkuran.text = ukuran!!.get(position)
    }

    override fun getItemCount(): Int = ukuran?.size!!
}