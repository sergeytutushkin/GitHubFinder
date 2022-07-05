package dev.tutushkin.githubfinder.ui.search.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.tutushkin.githubfinder.data.remote.SearchReposDto
import dev.tutushkin.githubfinder.databinding.ItemRepoBinding
import java.text.SimpleDateFormat
import java.util.*

class SearchViewHolder(
    private val binding: ItemRepoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchReposDto, clickListener: RepoClickListener) {
        binding.textViewName.text = item.name
        binding.textViewDescription.text = item.description
        binding.textViewLanguage.text = item.language
        binding.textViewStars.text = item.stargazers_count.toString()
        binding.textViewUpdated.text = item.updated_at.toDate()

        Glide.with(binding.root)
            .load(item.owner.avatar_url)
            .into(binding.imageViewAvatar)

        binding.root.setOnClickListener { clickListener.onItemClick(item.name) }
    }
}

private fun String.toDate(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(this)
    return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date)
}
