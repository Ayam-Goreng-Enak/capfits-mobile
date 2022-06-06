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

    @Multipart
    @POST("stories")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Header("Authorization") token: String,
        @Part("description") description: RequestBody,
    ): Call<FileUploadResponse>



}