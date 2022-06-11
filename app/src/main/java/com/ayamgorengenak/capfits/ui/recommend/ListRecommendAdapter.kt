package com.ayamgorengenak.capfits.ui.recommend

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.R
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ItemResultBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.net.URL
import javax.sql.DataSource

class ListRecommendAdapter(private val recommendList: MutableList<ListRekomendasiItem>) :
    RecyclerView.Adapter<ListRecommendAdapter.RecommendViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class RecommendViewHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recommend: ListRekomendasiItem) {
            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(recommend)
            }
            binding.apply {
                Glide.with(itemView).load(recommend.foto)
                    .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Glide.with(itemView).load(R.drawable.notfound).into(imageProduct)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: com.bumptech.glide.load.DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    }).into(imageProduct)

//            Log.e("aaaa", "$glide")
//            if (glide == null) {
//                Glide.with(itemView).load(R.drawable.notfound).into(imageProduct)
//            }

            titleProduct.text = recommend.nama_outfit
            priceProduct.text = recommend.harga_sewa.toString()
            locationProduct.text = recommend.lokasi
            starProduct.text = recommend.rating.toString()

//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailProductActivity::class.java)
//                    intent.putExtra("ListRekomendasiItem", recommend)
//                    val optionsCompat: ActivityOptionsCompat =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(
//                            itemView.context as Activity,
//                            Pair(imageProduct, "image"),
//                            Pair(titleProduct, "name"),
//                            Pair(priceProduct, "price"),
//                            Pair(locationProduct, "location"),
//                            Pair(starProduct, "star")
//                        )
//                    itemView.context.startActivity(intent, optionsCompat.toBundle())
//                }
        }
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
    val view = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return RecommendViewHolder((view))
}

override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
    holder.bind(recommendList[position])
}

override fun getItemCount(): Int = recommendList.size

interface OnItemClickCallback {
    fun onItemClicked(data: ListRekomendasiItem)
}

fun setOnClickCallBack(onItemClickCallback: OnItemClickCallback) {
    this.onItemClickCallback = onItemClickCallback
}
}