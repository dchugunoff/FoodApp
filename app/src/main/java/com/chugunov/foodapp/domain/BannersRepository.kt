package com.chugunov.foodapp.domain

import com.chugunov.foodapp.domain.models.BannerModel

interface BannersRepository {

    suspend fun getBanners(): List<BannerModel>
}