package com.ayamgorengenak.capfits.backend

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

//    @Multipart
    @POST("recommend")
    fun uploadImage(
        @Body base64img: String
    ): Call<FileUploadResponse>

    @POST("getOutfit")
    fun detail(
        @Body id_outfit: Int
    ): Call<FileDetailResponse>

}