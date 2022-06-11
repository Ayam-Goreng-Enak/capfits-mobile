package com.ayamgorengenak.capfits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayamgorengenak.capfits.WarnaAdapter.WarnaViewHolder
import com.ayamgorengenak.capfits.backend.ListRekomendasiItem
import com.ayamgorengenak.capfits.databinding.ItemResultBinding
import com.ayamgorengenak.capfits.databinding.ItemWarnaBinding
import com.ayamgorengenak.capfits.ui.recommend.ListRecommendAdapter
import com.bumptech.glide.Glide


class WarnaAdapter(private val warna: ArrayList<String>?) :
    RecyclerView.Adapter<WarnaAdapter.WarnaViewHolder>() {


    inner class WarnaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvWarna: TextView = itemView.findViewById((R.id.warna))
        var tvBulet: ImageView = itemView.findViewById((R.id.warnaBulet))
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
        holder.tvWarna.text = warna!![position]
        if(warna.get(position).equals("White") or warna!!.get(position).equals("Ivory")) holder.tvBulet.setImageResource(R.color.white)
        else if(warna.get(position).equals("Blue")) holder.tvBulet.setImageResource(R.color.blue)
        else if(warna.get(position).equals("Red")) holder.tvBulet.setImageResource(R.color.red)
        else if(warna.get(position).equals("Yellow")) holder.tvBulet.setImageResource(R.color.yellow)
        else if(warna.get(position).equals("Brown")) holder.tvBulet.setImageResource(R.color.brown)
        else if(warna.get(position).equals("Black")) holder.tvBulet.setImageResource(R.color.black)
        else if(warna.get(position).equals("Purple")) holder.tvBulet.setImageResource(R.color.purple_200)
        else if(warna.get(position).equals("Pink")) holder.tvBulet.setImageResource(R.color.pink)
        else if(warna.get(position).equals("Green")) holder.tvBulet.setImageResource(R.color.green)
        else if(warna.get(position).equals("Grey")) holder.tvBulet.setImageResource(R.color.gray_font)
        else if(warna.get(position).equals("Navy")) holder.tvBulet.setImageResource(R.color.navy_blue)
        else if(warna.get(position).equals("Gold")) holder.tvBulet.setImageResource(R.color.gold)
        else if(warna.get(position).equals("Orange")) holder.tvBulet.setImageResource(R.color.orange)
        else if(warna.get(position).equals("Beige")) holder.tvBulet.setImageResource(R.color.beige)
        else if(warna.get(position).equals("Turqoise Blue")) holder.tvBulet.setImageResource(R.color.teal)
        else if(warna.get(position).equals("Bronze")) holder.tvBulet.setImageResource(R.color.bronze)
        else if(warna.get(position).equals("Bronze")) holder.tvBulet.setImageResource(R.color.bronze)
        else if(warna.get(position).equals("Burgundy")) holder.tvBulet.setImageResource(R.color.burgundy)
        else if(warna.get(position).equals("Copper")) holder.tvBulet.setImageResource(R.color.copper)
        else if(warna.get(position).equals("Cream")) holder.tvBulet.setImageResource(R.color.cream)
        else if(warna.get(position).equals("Grey Melange")) holder.tvBulet.setImageResource(R.color.grey_melange)
        else if(warna.get(position).equals("Khaki")) holder.tvBulet.setImageResource(R.color.khaki)
        else if(warna.get(position).equals("Lavender")) holder.tvBulet.setImageResource(R.color.lavender)
        else if(warna.get(position).equals("Magenta")) holder.tvBulet.setImageResource(R.color.magenta)
        else if(warna.get(position).equals("Maroon")) holder.tvBulet.setImageResource(R.color.maroon)
        else if(warna.get(position).equals("Multi")) holder.tvBulet.setImageResource(R.color.multi)
        else if(warna.get(position).equals("Mushroom Brown")) holder.tvBulet.setImageResource(R.color.mushroom_brown)
        else if(warna.get(position).equals("Mustard")) holder.tvBulet.setImageResource(R.color.mustard)
        else if(warna.get(position).equals("Olive")) holder.tvBulet.setImageResource(R.color.olive)
        else if(warna.get(position).equals("Orange")) holder.tvBulet.setImageResource(R.color.orange)
        else if(warna.get(position).equals("Peach")) holder.tvBulet.setImageResource(R.color.peach)
        else if(warna.get(position).equals("Pink")) holder.tvBulet.setImageResource(R.color.pink)
        else if(warna.get(position).equals("Silver")) holder.tvBulet.setImageResource(R.color.silver)
        else if(warna.get(position).equals("Tan")) holder.tvBulet.setImageResource(R.color.tan)
        else if(warna.get(position).equals("Teal")) holder.tvBulet.setImageResource(R.color.teal)

        else holder.tvBulet.setImageResource(R.color.purple_200)
    }

    override fun getItemCount(): Int = warna?.size!!
}