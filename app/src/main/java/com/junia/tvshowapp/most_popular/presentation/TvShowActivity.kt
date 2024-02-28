package com.junia.tvshowapp.most_popular.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.junia.tvshowapp.R
import com.junia.tvshowapp.databinding.ActivityTvShowBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class TvShowActivity : AppCompatActivity() {

    private var page: Int = 1
    private val mostPopularViewModel : MostPopularViewModel by viewModels()
    private lateinit var mostPopularAdapter: MostPopularAdapter
    private lateinit var binding: ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)
        setRecyclerView()

        CoroutineScope(Dispatchers.IO).launch {
            mostPopularViewModel.getMostPopularTvShows(page)
            mostPopularViewModel.tvShowState.collectLatest { mostPopularState ->
                withContext(Dispatchers.Main){
                    if (mostPopularState.isLoading) {
                        binding.progressCircular.visibility = View.VISIBLE
                        binding.tvShowMostPopularNested.visibility = View.GONE
                    } else {
                        if (mostPopularState.error.isNotBlank()) {
                            binding.progressCircular.visibility = View.GONE
                            binding.tvShowMostPopularNested.visibility = View.GONE
                            Toast.makeText(this@TvShowActivity, mostPopularState.error, Toast.LENGTH_LONG).show()
                        } else {
                            binding.progressCircular.visibility = View.GONE
                            binding.tvShowMostPopularNested.visibility = View.VISIBLE
                            mostPopularAdapter.loadData(mostPopularState.tvShows)
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