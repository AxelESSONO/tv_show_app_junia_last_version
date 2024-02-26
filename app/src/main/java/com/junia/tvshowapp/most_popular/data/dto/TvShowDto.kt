package com.junia.tvshowapp.most_popular.data.dto


import com.google.gson.annotations.SerializedName
import com.junia.tvshowapp.most_popular.domain.model.TvShow

data class TvShowDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_thumbnail_path")
    val imageThumbnailPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("network")
    val network: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("status")
    val status: String
)

fun TvShowDto.toTvShow() : TvShow{
    return TvShow(
        id = id,
        imageThumbnailPath = imageThumbnailPath,
        name = name,
        network = "$network ($country)",
        startDate = "Started on $startDate",
        status = status
    )
}