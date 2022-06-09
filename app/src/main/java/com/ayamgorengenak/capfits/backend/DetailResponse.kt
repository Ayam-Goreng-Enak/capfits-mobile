package com.ayamgorengenak.capfits.backend

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class DetailResponse(

    @field:SerializedName("data")
    @Expose
    val data: MutableList<ListDetailItem>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)


@Parcelize
data class ListDetailItem(
    @SerializedName("id_outfit")
    @Expose
    val id_outfit: Int,

    @SerializedName("foto")
    @Expose
    val foto: String,

    @SerializedName("nama_outfit")
    @Expose
    val nama_outfit: String,

    @SerializedName("harga_sewa")
    @Expose
    val harga_sewa: Int,

    @SerializedName("lokasi")
    @Expose
    val lokasi: String,

    @SerializedName("rating")
    @Expose
    val rating: Int,

    ) : Parcelable