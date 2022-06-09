package com.ayamgorengenak.capfits.ui.recommend

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ItemResultBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.net.URL

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
                    .into(imageProduct)
                titleProduct.text = recommend.nama_outfit
                priceProduct.text = recommend.harga_sewa.toString()
                locationProduct.text = recommend.lokasi
                starProduct.text = recommend.rating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailProductActivity::class.java)
                    intent.putExtra("ListRekomendasiItem", recommend)
                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(imageProduct, "image"),
                            Pair(titleProduct, "name"),
                            Pair(priceProduct, "price"),
                            Pair(locationProduct, "location"),
                            Pair(starProduct, "star")
                        )
                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
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

    private fun linkToBitmap(urlimg: String): Bitmap? {
        try {
            val notFoundImgURL = URL(urlimg)
            var image = BitmapFactory.decodeStream(notFoundImgURL.openConnection().getInputStream())
            return image
        } catch (e: Exception) {
            val notFoundImgURL =
                URL("https://storage.googleapis.com/bangkit-capfits.appspot.com/Allbaseimage/notfound.png?Expires=1686236488&GoogleAccessId=firebase-adminsdk-ir8od%40bangkit-capfits.iam.gserviceaccount.com&Signature=eNAH9Ck38n%2B9ErDHqeLFM%2FppazyJNfDG1FDOHc1JywrKMocIj%2FhsZDC4LAOiz8dq0eQIOGMBWxOivmtMBLWdKBtuLoZaCp3JQwTLl6ePqjk1OR%2BN3Jw%2B3NQVEplkKCZ8Vxfbmantm9NADpoYWn73FRP1S%2BoTSCaDDUk6GB7XH8X669FTKoUMrrvODOt9TwoHNAyctaDgoZinsAdXBBtoxRAWhEazw8Jk%2FLEea3jtZjWf5tQ6qtCo%2B6UFSfBFwf1deANFBTIy1Bculrogffs4Y2NuK4F2zJYKSgsEFH1Sl7eUY5HtcprdKWoECEG%2BV20pWlyKVOGuPOwmi69e9vMBXQ%3D%3D")
            var image = BitmapFactory.decodeStream(notFoundImgURL.openConnection().getInputStream())
            return image
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListRekomendasiItem)
    }

    fun setOnClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}