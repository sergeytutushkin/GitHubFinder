package dev.tutushkin.githubfinder.ui.search.viewmodel

import dev.tutushkin.githubfinder.data.remote.SearchReposDto

sealed class ReposState {
    object Loading : ReposState()
    object EmptyResult : ReposState()
    object EmptyQuery : ReposState()
    data class SuccessResult(val result: List<SearchReposDto>) : ReposState()
    data class ErrorResult(val e: Throwable) : ReposState()
}
