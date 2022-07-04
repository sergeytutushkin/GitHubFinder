package dev.tutushkin.githubfinder.data.remote

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String,

    @SerializedName("bio")
    val bio: String? = null,

    @SerializedName("avatar_url")
    val avatar_url: String,

    @SerializedName("blog")
    val blog: String? = null,

    @SerializedName("twitter_username")
    val twitter_username: String? = null,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int
)
