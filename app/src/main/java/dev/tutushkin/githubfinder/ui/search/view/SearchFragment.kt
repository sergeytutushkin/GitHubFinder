package dev.tutushkin.githubfinder.ui.search.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.githubfinder.R
import dev.tutushkin.githubfinder.databinding.FragmentSearchBinding
import dev.tutushkin.githubfinder.ui.search.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var fragmentSearchBinding: FragmentSearchBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<SearchViewModel>()

        val binding = FragmentSearchBinding.bind(view)
        fragmentSearchBinding = binding

        binding.textView.text = viewModel.repos.toString()
    }

    override fun onDestroyView() {
        fragmentSearchBinding = null
        super.onDestroyView()
    }

}
