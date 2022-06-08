package com.ayamgorengenak.capfits.backend

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendOutfit(
    var nama_outfit: String,
    var harga_sewa: Int,
    var lokasi: String,
    val rating: Int,
    val foto: String
) : Parcelable