package dev.tutushkin.githubfinder.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.githubfinder.data.GitHubRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _repos = MutableLiveData<ReposState>()
    val repos: LiveData<ReposState> = _repos

    init {
        _repos.postValue(ReposState.EmptyQuery)
    }

    fun onNewQuery(query: String) {
        if (query.isEmpty()) {
            _repos.postValue(ReposState.EmptyQuery)
            return
        }

        viewModelScope.launch {
            _repos.postValue(ReposState.Loading)
            val result = repository.searchRepositories(query).items
            _repos.postValue(ReposState.SuccessResult(result))
        }
    }
}
