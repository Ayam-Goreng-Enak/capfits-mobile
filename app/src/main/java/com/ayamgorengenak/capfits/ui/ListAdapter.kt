package com.ayamgorengenak.capfits.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ItemCategoryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ListAdapter(private val storyList: MutableList<ListRekomendasiItem>) :
    RecyclerView.Adapter<ListAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: ListRekomendasiItem) {


            binding.apply {
//                Glide.with(itemView).load(story.photoUrl)
//                    .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()
//                    .into(photo)
                titleProduct.text = story.nama_outfit
                priceProduct.text = story.harga_sewa

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder((view))
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(storyList[position])
    }

    override fun getItemCount(): Int = storyList.size

}