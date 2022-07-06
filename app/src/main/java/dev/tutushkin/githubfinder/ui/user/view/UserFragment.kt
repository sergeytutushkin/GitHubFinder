package dev.tutushkin.githubfinder.ui.user.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.tutushkin.githubfinder.R
import dev.tutushkin.githubfinder.data.remote.UserResponse
import dev.tutushkin.githubfinder.databinding.FragmentUserBinding
import dev.tutushkin.githubfinder.ui.user.viewmodel.UserState
import dev.tutushkin.githubfinder.ui.user.viewmodel.UserViewModel

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<UserViewModel>()

        _binding = FragmentUserBinding.bind(view)

        val args: UserFragmentArgs by navArgs()

        viewModel.user.observe(viewLifecycleOwner, ::render)

        viewModel.getUser(args.user)
    }

    private fun render(state: UserState) {
        when (state) {
            is UserState.Loading -> {
                binding.loadingIndicator.show()
            }
            is UserState.SuccessResult -> {
                binding.loadingIndicator.hide()
                renderUser(state.result)
            }
            is UserState.ErrorResult -> {
                binding.loadingIndicator.hide()
                Toast.makeText(requireContext(), state.e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun renderUser(result: UserResponse) {
        with(binding) {
            textViewUserName.text = result.login
            textViewUserBio.text = result.bio
            textViewUserBlog.text = result.blog
            textViewUserTwitter.text = result.twitter_username
            textViewUserFollow.text =
                "${result.followers} followers / ${result.following} following"

            Glide.with(root)
                .load(result.avatar_url)
                .into(imageViewUserAvatar)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
