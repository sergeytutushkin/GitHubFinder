package dev.tutushkin.githubfinder.ui.search.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.githubfinder.R
import dev.tutushkin.githubfinder.data.remote.SearchReposDto
import dev.tutushkin.githubfinder.databinding.FragmentSearchBinding
import dev.tutushkin.githubfinder.ui.search.viewmodel.ReposState
import dev.tutushkin.githubfinder.ui.search.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var fragmentSearchBinding: FragmentSearchBinding? = null

    private lateinit var adapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<SearchViewModel>()

        val binding = FragmentSearchBinding.bind(view)
        fragmentSearchBinding = binding

        binding.searchIcon.setOnClickListener {
            viewModel.onNewQuery(binding.searchInput.text.toString())
        }

        binding.reposList.layoutManager = LinearLayoutManager(requireContext())
        val listener = object : RepoClickListener {
            override fun onItemClick(user: String) {
                TODO("Not yet implemented")
            }
        }
        adapter = SearchAdapter(listener)
        binding.reposList.adapter = adapter

        viewModel.repos.observe(viewLifecycleOwner, ::handleReposList)
    }

    private fun handleReposList(state: ReposState) {
        when (state) {
            is ReposState.Loading -> showPlaceholder("Loading...")
            is ReposState.EmptyResult -> showPlaceholder("Not found")
            is ReposState.EmptyQuery -> showPlaceholder("Empty query")
            is ReposState.SuccessResult -> showReposList(state.result)
            is ReposState.ErrorResult -> showPlaceholder("Something went wrong")
        }
    }

    private fun showPlaceholder(text: String) {
        adapter.submitList(emptyList())
        fragmentSearchBinding!!.reposPlaceholder.text = text
        fragmentSearchBinding!!.reposPlaceholder.visibility = View.VISIBLE
        fragmentSearchBinding!!.reposList.visibility = View.INVISIBLE
    }

    private fun showReposList(list: List<SearchReposDto>) {
        adapter.submitList(list)
        fragmentSearchBinding!!.reposList.visibility = View.VISIBLE
        fragmentSearchBinding!!.reposPlaceholder.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        fragmentSearchBinding = null
        super.onDestroyView()
    }

}
