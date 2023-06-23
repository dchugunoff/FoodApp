package com.chugunov.foodapp.data

import androidx.lifecycle.LiveData
import com.chugunov.foodapp.domain.ItemsRepository
import com.chugunov.foodapp.domain.models.FoodModel

class ItemsRepositoryImpl: ItemsRepository {

    override fun getItemsList(): LiveData<List<FoodModel>> {
        TODO("Not yet implemented")
    }
}