package com.chugunov.foodapp.data

import com.chugunov.foodapp.data.database.FoodItemDbModel
import com.chugunov.foodapp.domain.models.FoodModel

class ItemMapper {

    fun mapDtoToDbModel(dto: com.chugunov.foodapp.data.network.FoodModelDto) = FoodItemDbModel(
        category = dto.category,
        description = dto.description,
        img = dto.img,
        name = dto.name,
        price = dto.price
    )

    fun mapDbModelToEntity(dbModel: FoodItemDbModel) = FoodModel(
        category = dbModel.category,
        description = dbModel.description,
        img = dbModel.img,
        name = dbModel.name,
        price = dbModel.price
    )
}