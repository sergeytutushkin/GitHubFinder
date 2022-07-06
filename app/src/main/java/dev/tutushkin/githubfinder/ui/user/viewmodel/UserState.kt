package dev.tutushkin.githubfinder.ui.user.viewmodel

import dev.tutushkin.githubfinder.data.remote.UserResponse

sealed class UserState {
    object Loading : UserState()
    data class SuccessResult(val result: UserResponse) : UserState()
    data class ErrorResult(val e: Throwable) : UserState()
}
