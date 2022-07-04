package dev.tutushkin.githubfinder.ui.search.view

import androidx.recyclerview.widget.RecyclerView
import dev.tutushkin.githubfinder.data.remote.SearchReposDto
import dev.tutushkin.githubfinder.databinding.ItemRepoBinding

class SearchViewHolder(
    private val binding: ItemRepoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchReposDto, clickListener: RepoClickListener) {
        binding.textViewName.text = item.name
        binding.textViewDescription.text = item.description
        binding.textViewLanguage.text = item.language

        binding.root.setOnClickListener { clickListener.onItemClick(item.name) }
    }

}