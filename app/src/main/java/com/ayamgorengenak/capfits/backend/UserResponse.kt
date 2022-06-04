package com.ayamgorengenak.capfits.backend

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("loginResult")
    @Expose
    var loginResult: User? = null

    class User {
        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("token")
        @Expose
        var token: String? = null

    }
}