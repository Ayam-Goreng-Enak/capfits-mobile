package com.ayamgorengenak.capfits.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R

//class ListAdapter : RecyclerView.Adapter<ViewHolder>() {
//
//    private var items: List<String> = mutableListOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val text = items[position]
//        holder.label. = text
//
//    }
//
//    override fun getItemCount(): Int = items.size
//
//    fun setItems(items: List<String>) {
//        this.items = items
//    }
//
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val label: CardView = itemView.findViewById(R.id.cvCategory)
//    }
//}