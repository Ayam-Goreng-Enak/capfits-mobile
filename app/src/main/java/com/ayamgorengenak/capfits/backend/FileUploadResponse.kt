package com.ayamgorengenak.capfits.backend

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class FileUploadResponse(
    @field:SerializedName("listRekomendasi")
    val listRekomendasi: MutableList<ListRekomendasiItem>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

@Parcelize
data class ListRekomendasiItem(
    @field:SerializedName("nama_outfit")
    val nama_outfit: String,

    @field:SerializedName("harga_sewa")
    val harga_sewa: String,

    @field:SerializedName("lokasi")
    val lokasi: String,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("id_outfit")
    val id_outfit: String,
) : Parcelable