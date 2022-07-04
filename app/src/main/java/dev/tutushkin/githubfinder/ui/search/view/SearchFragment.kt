package dev.tutushkin.githubfinder.ui.search.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.githubfinder.R
import dev.tutushkin.githubfinder.databinding.FragmentSearchBinding
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

        binding.reposList.layoutManager = LinearLayoutManager(requireContext())
        val listener = object : RepoClickListener {
            override fun onItemClick(user: String) {
                TODO("Not yet implemented")
            }
        }
        adapter = SearchAdapter(listener)
        binding.reposList.adapter = adapter

//        binding.reposPlaceholder.text = "Loading..."
//        binding.reposList.visibility = View.INVISIBLE
//        binding.reposPlaceholder.visibility = View.VISIBLE
        adapter.submitList(viewModel.repos.value)
//        binding.reposPlaceholder.visibility = View.INVISIBLE
//        binding.reposList.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        fragmentSearchBinding = null
        super.onDestroyView()
    }

}
