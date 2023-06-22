package com.chugunov.foodapp.domain

import com.chugunov.foodapp.domain.models.BannerModel

interface BannersRepository {

    fun getBanners(): List<BannerModel>
}