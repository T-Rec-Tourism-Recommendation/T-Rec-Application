package com.example.t_rec.data.retrofit

import com.example.t_rec.data.response.DestinationResponse
import com.example.t_rec.data.response.DetailResponse
import com.example.t_rec.data.response.RecomendationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/destinasi")
    fun getDestinations(): Call<List<DestinationResponse>>

    @GET("/detailDestinasi/{id}")
    fun getDestinationDetail(@Path("id") id: Int): Call<DetailResponse>

    @POST("/search")
    fun searchDestinations(@Query("input") input: String): Call<List<DestinationResponse>>

    @POST("/filter")
    fun filterDestinations(
        @Query("cat") category: String?,
        @Query("kota") city: String?
    ): Call<List<DestinationResponse>>

    @POST("/recommendation")
    fun getRecommendations(
        @Query("kota") city: String?,
        @Query("text") text: String?
    ): Call<RecomendationResponse>

}