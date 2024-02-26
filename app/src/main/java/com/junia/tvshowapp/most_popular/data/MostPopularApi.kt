package com.junia.tvshowapp.most_popular.data

import com.junia.tvshowapp.most_popular.data.dto.MostPopularResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MostPopularApi {

    @GET("most-popular")
    suspend fun getMostPopular(
        @Query("page") page : Int
    ) : MostPopularResponseDto

}