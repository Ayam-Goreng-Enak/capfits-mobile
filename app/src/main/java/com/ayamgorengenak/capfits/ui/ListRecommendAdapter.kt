package com.ayamgorengenak.capfits.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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
import java.net.URL

class ListRecommendAdapter(private val listRecommend: MutableList<ListRekomendasiItem>) : RecyclerView.Adapter<ListRecommendAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id_outfit, foto, nama_outfit, harga_sewa, lokasi,rating) = listRecommend[position]
//        holder.imageProduct.setImageBitmap(linkToBitmap(foto))
        holder.titleProduct.text = nama_outfit
        holder.priceProduct.text = harga_sewa.toString()
        holder.locationProduct.text = lokasi
        holder.starProduct.text = rating.toString()
    }

    private fun linkToBitmap(urlimg:String): Bitmap? {
        try {
            val notFoundImgURL = URL(urlimg)
            var image = BitmapFactory.decodeStream(notFoundImgURL.openConnection().getInputStream())
            return image
        }
        catch (e: Exception) {
            val notFoundImgURL = URL("https://storage.googleapis.com/bangkit-capfits.appspot.com/Allbaseimage/notfound.png?Expires=1686236488&GoogleAccessId=firebase-adminsdk-ir8od%40bangkit-capfits.iam.gserviceaccount.com&Signature=eNAH9Ck38n%2B9ErDHqeLFM%2FppazyJNfDG1FDOHc1JywrKMocIj%2FhsZDC4LAOiz8dq0eQIOGMBWxOivmtMBLWdKBtuLoZaCp3JQwTLl6ePqjk1OR%2BN3Jw%2B3NQVEplkKCZ8Vxfbmantm9NADpoYWn73FRP1S%2BoTSCaDDUk6GB7XH8X669FTKoUMrrvODOt9TwoHNAyctaDgoZinsAdXBBtoxRAWhEazw8Jk%2FLEea3jtZjWf5tQ6qtCo%2B6UFSfBFwf1deANFBTIy1Bculrogffs4Y2NuK4F2zJYKSgsEFH1Sl7eUY5HtcprdKWoECEG%2BV20pWlyKVOGuPOwmi69e9vMBXQ%3D%3D")
            var image = BitmapFactory.decodeStream(notFoundImgURL.openConnection().getInputStream())
            return image
        }
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