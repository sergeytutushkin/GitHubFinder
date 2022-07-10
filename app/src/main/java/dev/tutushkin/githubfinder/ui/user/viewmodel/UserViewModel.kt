package dev.tutushkin.githubfinder.ui.user.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.githubfinder.data.GitHubRepository
import dev.tutushkin.githubfinder.ui.user.view.UserFragmentArgs
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: GitHubRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = UserFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _user = MutableLiveData<UserState>()
    val user: LiveData<UserState> = _user

    init {
        viewModelScope.launch {
            _user.postValue(UserState.Loading)

            val result = repository.getUser(args.user)

            if (result.isSuccess) {
                _user.postValue(UserState.SuccessResult(result.getOrThrow()))
            } else {
                _user.postValue(UserState.ErrorResult(Exception("User loading error")))
            }
        }
    }
}
