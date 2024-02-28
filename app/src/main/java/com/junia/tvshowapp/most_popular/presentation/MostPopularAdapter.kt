package com.junia.tvshowapp.most_popular.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junia.tvshowapp.R
import com.junia.tvshowapp.databinding.TvShowBinding
import com.junia.tvshowapp.most_popular.domain.model.TvShow

class MostPopularAdapter(
    private val listener : (TvShow) -> Unit
) : RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder>() {

    private var tvShowList : MutableList<TvShow> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        val binding : TvShowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tv_show,
            parent,
            false
        )
        return MostPopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) {
        holder.bindTvShow(tvShowList[position], listener)
    }

    override fun getItemCount(): Int = tvShowList.size

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(tvShows: List<TvShow>){
        this.tvShowList = tvShows.toMutableList()
        notifyDataSetChanged()
    }

    class MostPopularViewHolder(
        private val binding: TvShowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTvShow(tvShow: TvShow, listener: (TvShow) -> Unit){
            binding.tvShow = tvShow
            Glide.with(binding.root.context)
                .load(tvShow.imageThumbnailPath)
                .into(binding.tvShowImage)

            binding.root.setOnClickListener { listener(tvShow) }
        }
    }
}