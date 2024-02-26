package com.junia.tvshowapp.most_popular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junia.tvshowapp.most_popular.domain.use_case.MostPopularUseCase
import com.junia.tvshowapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MostPopularViewModel @Inject constructor(
    private val mostPopularUseCase: MostPopularUseCase
) : ViewModel() {

    private val _tvShowState: MutableStateFlow<MostPopularState> = MutableStateFlow(MostPopularState())
    var tvShowState: StateFlow<MostPopularState> = _tvShowState

    fun getMostPopularTvShows(page : Int) = viewModelScope.launch(Dispatchers.IO) {
        mostPopularUseCase(page).collectLatest { resource ->

            when(resource){
                is Resource.Error -> {
                    _tvShowState.value = MostPopularState(error = "Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _tvShowState.value = MostPopularState(isLoading = true)
                }
                is Resource.Success -> {
                    _tvShowState.value = MostPopularState(
                        tvShows = resource.data?: emptyList()
                    )
                }
            }
        }
    }
}