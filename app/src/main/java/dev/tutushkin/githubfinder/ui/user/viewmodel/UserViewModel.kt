package dev.tutushkin.githubfinder.ui.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.githubfinder.data.GitHubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _user = MutableLiveData<UserState>()
    val user: LiveData<UserState> = _user

    suspend fun handleUser(name: String) {
        viewModelScope.launch {
            _user.postValue(UserState.Loading)

            val result = repository.getUser(name)

            if (result.isSuccess) {
                _user.postValue(UserState.SuccessResult(result.getOrThrow()))
            } else {
                _user.postValue(UserState.ErrorResult(Exception("Error loading from the server")))
            }
        }
    }
}
