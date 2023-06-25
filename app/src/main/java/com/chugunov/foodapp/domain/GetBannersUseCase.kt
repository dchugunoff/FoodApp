package com.chugunov.foodapp.domain

import com.chugunov.foodapp.domain.models.BannerModel

class GetBannersUseCase(
    private val repository: BannersRepository
) {

    suspend fun execute(): List<BannerModel> {
        return repository.getBanners()
    }
}