package com.chugunov.foodapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chugunov.foodapp.data.BannerRepositoryImpl
import com.chugunov.foodapp.data.network.ApiFactory
import com.chugunov.foodapp.data.network.FoodItemDto
import com.chugunov.foodapp.domain.GetBannersUseCase
import com.chugunov.foodapp.domain.models.BannerModel
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _bannersList = MutableLiveData<List<BannerModel>>()
    val bannersList: LiveData<List<BannerModel>> = _bannersList

    private val _itemList = MutableLiveData<List<FoodItemDto>>()
    val itemList: LiveData<List<FoodItemDto>> = _itemList

    private val repository = BannerRepositoryImpl()

    private val getBannersUseCase = GetBannersUseCase(repository)

    init {
        getBanners()
        viewModelScope.launch {
            getList()
            Log.d("ApiService", "finish scope")
        }
    }

    private fun getBanners() {
        _bannersList.value = getBannersUseCase.execute()
    }

    private suspend fun getList() {
        try {
            val itemList = ApiFactory.apiService.getProductsList()
            _itemList.postValue(itemList)
            Log.d("ApiService", "${_itemList.value}")
            Log.d("ApiService", "${ApiFactory.apiService.getProductsList()}")
        } catch (e: Exception) {
            Log.e("ApiService", "Error getting product list", e)
        }
    }
}