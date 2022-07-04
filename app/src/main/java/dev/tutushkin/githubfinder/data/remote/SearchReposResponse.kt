package dev.tutushkin.githubfinder.data.remote

import com.google.gson.annotations.SerializedName

data class SearchReposResponse(
    @SerializedName("total_count")
    val count: Int,

    @SerializedName("items")
    val name: List<SearchReposDto>
)
