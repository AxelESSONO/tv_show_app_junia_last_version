package com.junia.tvshowapp.most_popular.presentation

import com.junia.tvshowapp.most_popular.domain.model.TvShow

data class MostPopularState(
    val isLoading : Boolean = false,
    val tvShows : List<TvShow> = emptyList(),
    val error : String = ""
)