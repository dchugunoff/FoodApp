package com.chugunov.foodapp.domain

import androidx.lifecycle.LiveData
import com.chugunov.foodapp.domain.models.FoodModel

interface ItemsRepository {

    fun getItemsList(): LiveData<List<FoodModel>>
}