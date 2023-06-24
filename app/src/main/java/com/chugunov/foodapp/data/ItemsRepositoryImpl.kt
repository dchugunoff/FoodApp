package com.chugunov.foodapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.chugunov.foodapp.data.database.FoodItemDao
import com.chugunov.foodapp.data.database.FoodItemDbModel
import com.chugunov.foodapp.data.network.FoodModelDto
import com.chugunov.foodapp.domain.ItemsRepository
import com.chugunov.foodapp.domain.models.FoodModel

class ItemsRepositoryImpl(
    private val mapper: ItemMapper,
    private val foodItemDao: FoodItemDao
) : ItemsRepository {


    override fun getItemsList(): LiveData<List<FoodModel>> {
        val dbModelList: LiveData<List<FoodItemDbModel>> = foodItemDao.getFoodList()
        return dbModelList.map { it -> it.map { mapper.mapDbModelToEntity(it) } }
    }

    suspend fun insertItem(items: List<FoodModelDto>) {
        val dtoModels = items.map { mapper.mapDtoToDbModel(it) }
        foodItemDao.insertFoodList(dtoModels)
    }
}