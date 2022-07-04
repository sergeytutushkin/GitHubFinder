package dev.tutushkin.githubfinder.data.remote

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatar_url: String
)
