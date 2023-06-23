package com.chugunov.foodapp.domain

class GetItemListUseCase(
    private val repository: ItemsRepository
) {
    suspend fun getItems() {
        repository.getItemsList()
    }
}