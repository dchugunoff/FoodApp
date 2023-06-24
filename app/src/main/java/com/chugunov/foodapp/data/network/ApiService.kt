package com.chugunov.foodapp.data.network

import retrofit2.http.GET

interface ApiService {

    @GET("getproducts/")
    suspend fun getProductsList(): List<FoodModelDto>
}