package dev.tutushkin.githubfinder.data.remote

import com.google.gson.annotations.SerializedName

data class SearchReposDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("language")
    val language: String? = null,

    @SerializedName("updated_at")
    val updated_at: String,

    @SerializedName("owner")
    val owner: OwnerDto,

    @SerializedName("stargazers_count")
    val stargazers_count: Int
)
