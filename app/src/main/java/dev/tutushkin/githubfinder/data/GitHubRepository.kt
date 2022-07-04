package dev.tutushkin.githubfinder.data

import dev.tutushkin.githubfinder.data.remote.GitHubApi

class GitHubRepository(
    private val gitHubApi: GitHubApi
) {
    suspend fun searchRepositories(name: String) = gitHubApi.searchRepositories(name)

    suspend fun getUser(user: String) = gitHubApi.getUser(user)
}
