
package com.junia.tvshowapp.most_popular.domain.repository

import com.junia.tvshowapp.most_popular.data.dto.MostPopularResponseDto

interface MostPopularRepository {
    suspend fun getMostPopularTvShows(
        page : Int
    ) : MostPopularResponseDto
}