package com.chugunov.foodapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chugunov.foodapp.data.BannerRepositoryImpl
import com.chugunov.foodapp.domain.GetBannersUseCase
import com.chugunov.foodapp.domain.models.BannerModel

class MainViewModel: ViewModel() {

    private val _bannersList = MutableLiveData<List<BannerModel>>()
    val bannersList: LiveData<List<BannerModel>> = _bannersList

    private val repository = BannerRepositoryImpl()

    private val getBannersUseCase = GetBannersUseCase(repository)

    init {
        getBanners()
    }

    private fun getBanners() {
        _bannersList.value = getBannersUseCase.execute()
    }
}