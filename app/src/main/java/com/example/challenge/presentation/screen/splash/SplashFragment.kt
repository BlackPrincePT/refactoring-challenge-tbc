package com.example.challenge.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challenge.databinding.FragmentSplashBinding
import com.example.challenge.presentation.core.BaseFragment
import com.example.challenge.presentation.util.collectLatest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun setup() {
        subscribeToViewStateUpdates()
    }

    private fun subscribeToViewStateUpdates() {
        collectLatest(viewModel.hasSavedSession) { hasSavedSession ->
            if (hasSavedSession)
                findNavController().navigate(SplashFragmentDirections.splashToConnections())
            else
                findNavController().navigate(SplashFragmentDirections.splashToLogIn())
        }
    }
}
