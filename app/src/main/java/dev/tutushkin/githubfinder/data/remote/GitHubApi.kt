package dev.tutushkin.githubfinder.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("/search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String
    ): SearchReposResponse

    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") user: String
    ): UserResponse
}
