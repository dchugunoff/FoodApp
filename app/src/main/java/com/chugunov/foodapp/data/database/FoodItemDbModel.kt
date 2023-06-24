package com.chugunov.foodapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class FoodItemDbModel(
    val category: String,
    val description: String,
    val img: String,
    @PrimaryKey
    val name: String,
    val price: String
)