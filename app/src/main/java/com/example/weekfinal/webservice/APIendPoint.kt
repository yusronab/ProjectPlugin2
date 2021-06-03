package com.example.weekfinal.webservice

import com.example.weekfinal.model.Post
import com.example.weekfinal.model.User
import com.example.weekfinal.response.ListResponse
import com.example.weekfinal.response.SingleResponse
import retrofit2.Call
import retrofit2.http.*

interface APIendPoint {

    @FormUrlEncoded
    @POST("auth/sign-up")
    fun register(
            @Field("name") name: String,
            @Field("username") username: String,
            @Field("email") email: String,
            @Field("password") password: String
    ): Call<SingleResponse<User>>

    @FormUrlEncoded
    @POST("auth/sign-in")
    fun login(
            @Field("username") username: String,
            @Field("password") password: String
    ): Call<SingleResponse<User>>

    @GET("barang")
    fun getAllPost(): Call<ListResponse<Post>>

    @GET("barang/{id}")
    fun getPostById(@Path("id")id: Int): Call<ListResponse<Post>>

    @DELETE("barang/{id}")
    fun deletePost(@Path("id")id: Int): Call<Void>

    @FormUrlEncoded
    @POST("barang")
    fun addPost(
            @Field("nama") nama: String,
            @Field("kode") kode: Int
    ): Call<ListResponse<Post>>

    @FormUrlEncoded
    @PUT("barang/{id}")
    fun updatePost(
            @Path("id") id: Int,
            @Field("nama") nama: String,
            @Field("kode") kode: String
    ): Call<SingleResponse<Post>>


}