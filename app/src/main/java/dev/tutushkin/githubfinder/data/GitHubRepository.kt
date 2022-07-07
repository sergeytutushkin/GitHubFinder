package dev.tutushkin.githubfinder.data

import dev.tutushkin.githubfinder.data.remote.GitHubApi
import dev.tutushkin.githubfinder.data.remote.SearchReposResponse
import dev.tutushkin.githubfinder.data.remote.UserResponse
import javax.inject.Inject

interface GitHubRepository {
    suspend fun searchRepositories(name: String): SearchReposResponse
    suspend fun getUser(name: String): Result<UserResponse>
}

class GitHubRepositoryImpl @Inject constructor(
    private val gitHubApi: GitHubApi
) : GitHubRepository {
    override suspend fun searchRepositories(name: String) = gitHubApi.searchRepositories(name)

    override suspend fun getUser(name: String): Result<UserResponse> =
        runCatching {
            gitHubApi.getUser(name)
        }

}
