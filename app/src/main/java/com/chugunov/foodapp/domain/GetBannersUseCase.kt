package com.chugunov.foodapp.domain

import com.chugunov.foodapp.domain.models.BannerModel

class GetBannersUseCase(
    private val repository: BannersRepository
) {

    fun execute(): List<BannerModel> {
        return repository.getBanners()
    }
}