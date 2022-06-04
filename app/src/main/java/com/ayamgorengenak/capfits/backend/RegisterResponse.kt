package com.ayamgorengenak.capfits.backend

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse   {
    @SerializedName("message")
    @Expose
    var message: String? = null
}