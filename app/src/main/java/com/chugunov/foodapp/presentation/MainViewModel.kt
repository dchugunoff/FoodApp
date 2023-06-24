package com.chugunov.foodapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chugunov.foodapp.data.BannerRepositoryImpl
import com.chugunov.foodapp.data.ItemMapper
import com.chugunov.foodapp.data.ItemsRepositoryImpl
import com.chugunov.foodapp.data.database.AppDatabase
import com.chugunov.foodapp.data.network.ApiFactory
import com.chugunov.foodapp.domain.GetBannersUseCase
import com.chugunov.foodapp.domain.GetItemListUseCase
import com.chugunov.foodapp.domain.models.BannerModel
import com.chugunov.foodapp.domain.models.FoodModel
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : ViewModel() {

    private val _bannersList = MutableLiveData<List<BannerModel>>()
    val bannersList: LiveData<List<BannerModel>> = _bannersList

    private val _itemList = MutableLiveData<List<FoodModel>>()
    val itemList: LiveData<List<FoodModel>> = _itemList

    private val repository = BannerRepositoryImpl()
    private val itemsRepository = ItemsRepositoryImpl(
        mapper = ItemMapper(),
        foodItemDao = AppDatabase.getInstance(application).foodItemDao()
    )

    private val getBannersUseCase = GetBannersUseCase(repository)
    private val getItemListUseCase = GetItemListUseCase(itemsRepository)

    init {
        getBanners()
        viewModelScope.launch {
            insertItems()
            getFoodList()
        }
    }

    private fun getBanners() {
        _bannersList.value = getBannersUseCase.execute()
    }

    private fun getFoodList() {
        getItemListUseCase.getItems().observeForever { listFoodModel ->
            _itemList.value = listFoodModel
            Log.d("ApiServiceIL", "$_itemList")
        }
    }

    private suspend fun insertItems() {
        val daoModels = ApiFactory.apiService.getProductsList()
        itemsRepository.insertItem(daoModels)
        Log.d("ApiServiceDM", "$daoModels")
    }
}