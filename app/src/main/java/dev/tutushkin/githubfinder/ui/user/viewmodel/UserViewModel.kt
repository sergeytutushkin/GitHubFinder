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

    fun getUser(name: String) {
        viewModelScope.launch {
            _user.postValue(UserState.Loading)
            val result = repository.getUser(name)
            _user.postValue(UserState.SuccessResult(result))
        }
    }
}
