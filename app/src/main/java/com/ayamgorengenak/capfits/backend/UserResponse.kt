package com.ayamgorengenak.capfits.backend

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("loginResult")
    @Expose
    var loginResult: User? = null

    class User {
        @SerializedName("nama")
        @Expose
        var nama: String? = null

        @SerializedName("token")
        @Expose
        var token: String? = null

    }
}