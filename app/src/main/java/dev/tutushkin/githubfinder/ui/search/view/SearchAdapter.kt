package dev.tutushkin.githubfinder.ui.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.tutushkin.githubfinder.data.remote.SearchReposDto
import dev.tutushkin.githubfinder.databinding.ItemRepoBinding

class SearchAdapter(
    private val clickListener: RepoClickListener
) : ListAdapter<SearchReposDto, SearchViewHolder>(ReposDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

private class ReposDiffCallback : DiffUtil.ItemCallback<SearchReposDto>() {

    override fun areItemsTheSame(oldItem: SearchReposDto, newItem: SearchReposDto): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: SearchReposDto, newItem: SearchReposDto): Boolean =
        oldItem == newItem
}

interface RepoClickListener {
    fun onItemClick(user: String)
}