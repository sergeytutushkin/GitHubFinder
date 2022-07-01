package dev.tutushkin.githubfinder.ui.user.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.tutushkin.githubfinder.R
import dev.tutushkin.githubfinder.ui.user.viewmodel.UserViewModel

class UserFragment : Fragment(R.layout.fragment_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<UserViewModel>()
    }

}
