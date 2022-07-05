package dev.tutushkin.githubfinder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.tutushkin.githubfinder.data.GitHubRepository
import dev.tutushkin.githubfinder.data.GitHubRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface GitHubModule {

    @Binds
    fun bindGitHubRepository(impl: GitHubRepositoryImpl): GitHubRepository
}
