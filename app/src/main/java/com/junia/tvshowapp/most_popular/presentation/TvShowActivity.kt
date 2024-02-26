package com.junia.tvshowapp.most_popular.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.junia.tvshowapp.R
import com.junia.tvshowapp.databinding.ActivityTvShowBinding
import com.junia.tvshowapp.most_popular.domain.model.TvShow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class TvShowActivity : AppCompatActivity() {

    private var tempBankList = arrayListOf<TvShow>()
    private var page: Int = 1

    private val mostPopularViewModel : MostPopularViewModel by viewModels()
    private lateinit var mostPopularAdapter: MostPopularAdapter
    private lateinit var binding: ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
        setRecyclerView()

        GlobalScope.launch(Dispatchers.IO) {
            mostPopularViewModel.getMostPopularTvShows(1)
            withContext(Dispatchers.Main){
                mostPopularViewModel.tvShowState.collectLatest { mostPopularState ->
                    if (mostPopularState.isLoading) {
                    } else {
                        if (mostPopularState.error.isNotBlank()) {
                        } else {
                            tempBankList.addAll(
                                mostPopularState.tvShows
                            )
                            mostPopularAdapter.loadData(tempBankList)
                        }
                    }
                }
            }
        }
    }

    private fun setRecyclerView() {
        mostPopularAdapter = MostPopularAdapter {
            /***
             * Le code pour gérer le clic sur un item du recyclerView
             */
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }

        binding.tvShowMostPopularRecycler.apply {
            adapter = mostPopularAdapter
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
        }
    }
}