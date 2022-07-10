package dev.tutushkin.githubfinder.ui.search.viewmodel

import dev.tutushkin.githubfinder.data.remote.SearchReposDto

sealed class SearchState {
    object Loading : SearchState()
    object EmptyResult : SearchState()
    object EmptyQuery : SearchState()
    data class SuccessResult(val result: List<SearchReposDto>) : SearchState()
    data class ErrorResult(val e: Throwable) : SearchState()
}
