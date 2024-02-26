package com.junia.tvshowapp.most_popular.domain.model

data class TvShow(
    val id: Int,
    val imageThumbnailPath: String,
    val name: String,
    val network: String,
    val startDate: String,
    val status: String
)
