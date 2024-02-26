package com.junia.tvshowapp.di

import com.junia.tvshowapp.most_popular.data.MostPopularApi
import com.junia.tvshowapp.most_popular.data.repository.MostPopularRepositoryImpl
import com.junia.tvshowapp.most_popular.domain.repository.MostPopularRepository
import com.junia.tvshowapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TvShowModule {
    @Provides
    @Singleton
    fun provideMostPopularApi() : MostPopularApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MostPopularApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMostPopularRepository(
        mostPopularApi: MostPopularApi) : MostPopularRepository{
        return MostPopularRepositoryImpl(mostPopularApi)
    }
}