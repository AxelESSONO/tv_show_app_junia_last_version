package com.junia.tvshowapp.most_popular.domain.use_case

import com.junia.tvshowapp.most_popular.domain.repository.MostPopularRepository
import javax.inject.Inject

class MostPopularUseCase @Inject constructor(
    private val mostPopularRepository: MostPopularRepository
) {

    //operator fun invoke(page : Int)

}