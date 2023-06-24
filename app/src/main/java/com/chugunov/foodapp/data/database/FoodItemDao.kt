package com.chugunov.foodapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodItemDao {

    @Query("SELECT * FROM menu")
    fun getFoodList(): LiveData<List<FoodItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList(foodList: List<FoodItemDbModel>)
}