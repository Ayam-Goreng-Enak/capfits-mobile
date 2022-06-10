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
        if(warna!!.get(position).equals("White") or warna!!.get(position).equals("Ivory")) holder.tvBulet.setBackgroundResource(R.color.white)
        else if(warna!!.get(position).equals("Blue")) holder.tvBulet.setBackgroundResource(R.color.blue)
        else if(warna!!.get(position).equals("Red")) holder.tvBulet.setBackgroundResource(R.color.red)
        else if(warna!!.get(position).equals("Yellow")) holder.tvBulet.setBackgroundResource(R.color.yellow)
        else if(warna!!.get(position).equals("Brown")) holder.tvBulet.setBackgroundResource(R.color.brown)
        else if(warna!!.get(position).equals("Black")) holder.tvBulet.setBackgroundResource(R.color.black)
        else holder.tvBulet.setBackgroundResource(R.color.purple_200)
    }

    override fun getItemCount(): Int = warna?.size!!
}