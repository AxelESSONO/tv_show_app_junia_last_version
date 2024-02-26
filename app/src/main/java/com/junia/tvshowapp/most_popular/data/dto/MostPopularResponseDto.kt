package com.junia.tvshowapp.most_popular.data.dto


import com.google.gson.annotations.SerializedName
import com.junia.tvshowapp.most_popular.domain.model.MostPopularResponse

data class MostPopularResponseDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: String,
    @SerializedName("tv_shows")
    val tvShows: List<TvShowDto>
)

fun MostPopularResponseDto.toMostPopularResponse() : MostPopularResponse{
    return MostPopularResponse(
        page = page,
        pages = pages,
        total = total,
        tvShows = tvShows
    )
}