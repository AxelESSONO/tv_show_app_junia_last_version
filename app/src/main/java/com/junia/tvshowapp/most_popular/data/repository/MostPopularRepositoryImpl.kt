package com.junia.tvshowapp.most_popular.data.repository

import com.junia.tvshowapp.most_popular.data.MostPopularApi
import com.junia.tvshowapp.most_popular.data.dto.MostPopularResponseDto
import com.junia.tvshowapp.most_popular.domain.repository.MostPopularRepository
import javax.inject.Inject

class MostPopularRepositoryImpl @Inject constructor (
    private val mostPopularApi: MostPopularApi
) : MostPopularRepository {
    override suspend fun getMostPopularTvShows(page: Int): MostPopularResponseDto {
        return mostPopularApi.getMostPopular(page)
    }
}