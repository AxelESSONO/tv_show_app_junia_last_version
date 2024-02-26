package com.junia.tvshowapp.most_popular.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.junia.tvshowapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowActivity : AppCompatActivity() {

    private val mostPopularViewModel : MostPopularViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show)
    }
}