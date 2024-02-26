package com.junia.tvshowapp.most_popular.domain.model

import com.junia.tvshowapp.most_popular.data.dto.TvShowDto
data class MostPopularResponse(
    val page: Int,
    val pages: Int,
    val total: String,
    val tvShows: List<TvShowDto>
)
