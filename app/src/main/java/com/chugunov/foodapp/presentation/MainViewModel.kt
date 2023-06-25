package com.chugunov.foodapp.presentation

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chugunov.foodapp.R
import com.chugunov.foodapp.data.BannerRepositoryImpl
import com.chugunov.foodapp.data.ItemMapper
import com.chugunov.foodapp.data.ItemsRepositoryImpl
import com.chugunov.foodapp.data.database.AppDatabase
import com.chugunov.foodapp.data.network.ApiFactory
import com.chugunov.foodapp.domain.GetBannersUseCase
import com.chugunov.foodapp.domain.GetItemListUseCase
import com.chugunov.foodapp.domain.models.BannerModel
import com.chugunov.foodapp.domain.models.FoodModel
import kotlinx.coroutines.Dispatchers
import com.chugunov.foodapp.presentation.adapters.ItemsAdapter
import kotlinx.coroutines.launch
import okio.IOException

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

    private var itemsAdapter = ItemsAdapter()

    init {
        getBanners()
        viewModelScope.launch {
            insertItems()
            getFoodList()
        }
    }

    private fun getBanners() {
        viewModelScope.launch(Dispatchers.IO) {
            val banners = getBannersUseCase.execute()
            _bannersList.postValue(banners)
        }
    }

    private fun getFoodList() {
        viewModelScope.launch {
            getItemListUseCase.getItems().observeForever { listFoodModel ->
                _itemList.postValue(listFoodModel)
                Log.d("ApiServiceIL", "$_itemList")
            }
        }
    }

    private suspend fun insertItems() {
        viewModelScope.launch {
            try {
                val daoModels = ApiFactory.apiService.getProductsList()
                itemsRepository.insertItem(daoModels)
                Log.d("ApiServiceDM", "$daoModels")
            } catch (e: IOException) {
                Toast.makeText(
                    application,
                    application.getString(R.string.exception_network),
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        }
    }
}