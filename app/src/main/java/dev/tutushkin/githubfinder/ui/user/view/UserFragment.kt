package dev.tutushkin.githubfinder.ui.user.view

import android.os.Bundle
import android.view.View
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

    private var fragmentUserBinding: FragmentUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<UserViewModel>()

        val binding = FragmentUserBinding.bind(view)
        fragmentUserBinding = binding

        val args: UserFragmentArgs by navArgs()

        viewModel.user.observe(viewLifecycleOwner, ::handleUser)

        viewModel.getUser(args.user)
    }

    private fun handleUser(state: UserState) {
        when (state) {
            is UserState.Loading -> {
                fragmentUserBinding!!.loadingIndicator.show()
            }
            is UserState.SuccessResult -> {
                fragmentUserBinding!!.loadingIndicator.hide()
                showUser(state.result)
            }
            is UserState.ErrorResult -> {
                fragmentUserBinding!!.loadingIndicator.hide()
            }
        }

    }

    private fun showUser(result: UserResponse) {
        with(fragmentUserBinding!!) {
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
        fragmentUserBinding = null
        super.onDestroyView()
    }

}
