package dev.tutushkin.githubfinder.ui.search.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.githubfinder.R
import dev.tutushkin.githubfinder.data.remote.SearchReposDto
import dev.tutushkin.githubfinder.databinding.FragmentSearchBinding
import dev.tutushkin.githubfinder.ui.search.viewmodel.ReposState
import dev.tutushkin.githubfinder.ui.search.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<SearchViewModel>()

        _binding = FragmentSearchBinding.bind(view)

        binding.searchIcon.setOnClickListener {
            it.hideKeyboard()
            viewModel.onNewQuery(binding.searchInput.text.toString())
        }

        binding.reposList.layoutManager = LinearLayoutManager(requireContext())
        val listener = object : RepoClickListener {
            // TODO Handle through the ViewModel
            override fun onItemClick(user: String) {
                val action = SearchFragmentDirections.actionSearchFragmentToUserFragment(user)
                view.findNavController().navigate(action)
            }
        }
        adapter = SearchAdapter(listener)
        binding.reposList.adapter = adapter

        viewModel.repos.observe(viewLifecycleOwner, ::handleReposList)
    }

    // TODO Handling errors
    private fun handleReposList(state: ReposState) {
        when (state) {
            is ReposState.Loading -> showPlaceholder("Loading...")
            is ReposState.EmptyResult -> showPlaceholder("Not found")
            is ReposState.EmptyQuery -> showPlaceholder("Enter a query")
            is ReposState.SuccessResult -> showReposList(state.result)
            is ReposState.ErrorResult -> showPlaceholder("Something went wrong")
        }
    }

    private fun showPlaceholder(text: String) {
        adapter.submitList(emptyList())
        binding.reposPlaceholder.text = text
        binding.reposPlaceholder.visibility = View.VISIBLE
        binding.reposList.visibility = View.INVISIBLE
    }

    private fun showReposList(list: List<SearchReposDto>) {
        adapter.submitList(list)
        binding.reposList.visibility = View.VISIBLE
        binding.reposPlaceholder.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
