package com.ayamgorengenak.capfits.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.backend.RecommendOutfit
import com.ayamgorengenak.capfits.databinding.ItemCategoryBinding

class ListRecommendAdapter(private val listRecommend: ArrayList<RecommendOutfit>) : RecyclerView.Adapter<ListRecommendAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nama_outfit, harga_sewa, lokasi, rating, foto) = listRecommend[position]
        holder.imageProduct.setImageResource(foto)
        holder.titleProduct.text = nama_outfit
        holder.priceProduct.text = harga_sewa
        holder.locationProduct.text = lokasi
        holder.starProduct.text = rating
    }

    override fun getItemCount(): Int = listRecommend.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageProduct: ImageView = itemView.findViewById(R.id.imageProduct)
        var titleProduct: TextView = itemView.findViewById(R.id.titleProduct)
        var priceProduct: TextView = itemView.findViewById(R.id.priceProduct)
        var locationProduct: TextView = itemView.findViewById(R.id.locationProduct)
        var starProduct: TextView = itemView.findViewById(R.id.starProduct)
    }
}