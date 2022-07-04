package dev.tutushkin.githubfinder.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tutushkin.githubfinder.data.GitHubRepository
import dev.tutushkin.githubfinder.data.remote.SearchReposDto
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _repos = MutableLiveData<List<SearchReposDto>>()
    val repos: LiveData<List<SearchReposDto>> = _repos

    init {
        viewModelScope.launch {
            _repos.postValue(repository.searchRepositories("allmovies").name)
        }
    }
}
