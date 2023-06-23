package com.chugunov.foodapp.data.network

data class FoodItemDto(
    val category: String,
    val description: String,
    val img: String,
    val name: String,
    val price: String
)