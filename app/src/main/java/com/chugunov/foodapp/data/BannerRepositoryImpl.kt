package com.chugunov.foodapp.data

import com.chugunov.foodapp.R
import com.chugunov.foodapp.domain.models.BannerModel
import com.chugunov.foodapp.domain.BannersRepository

class BannerRepositoryImpl: BannersRepository {

    override fun getBanners(): List<BannerModel> {
        return listOf(
            BannerModel(drawable = R.drawable.banner_1),
            BannerModel(drawable = R.drawable.banner_4)
        )
    }
}