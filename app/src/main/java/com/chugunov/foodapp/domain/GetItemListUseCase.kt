package com.chugunov.foodapp.domain

import androidx.lifecycle.LiveData
import com.chugunov.foodapp.domain.models.FoodModel

class GetItemListUseCase(
    private val repository: ItemsRepository
) {
    fun getItems(): LiveData<List<FoodModel>> {
        return repository.getItemsList()
    }
}