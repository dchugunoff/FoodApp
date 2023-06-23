package com.chugunov.foodapp.data

import com.chugunov.foodapp.data.network.FoodItemDto
import com.chugunov.foodapp.domain.models.FoodModel

class ItemMapper {

    fun mapDtoToFoodModel(dto: FoodItemDto) = FoodModel(
        category = dto.category,
        description = dto.description,
        img = dto.img,
        name = dto.name,
        price = dto.price
    )
}