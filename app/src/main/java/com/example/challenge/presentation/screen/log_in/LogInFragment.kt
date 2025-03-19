package com.example.challenge.presentation.screen.log_in

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.challenge.databinding.FragmentLogInBinding
import com.example.challenge.presentation.core.BaseFragment
import com.example.challenge.presentation.core.toStringResId
import com.example.challenge.presentation.util.collectLatest
import com.example.challenge.presentation.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()

    override fun setup() {
        subscribeToViewStateUpdates()
    }

    override fun listeners() = with(binding) {
        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() = with(binding) {
        val enteredEmail = etEmail.text.toString()
        val enteredPassword = etPassword.text.toString()

        viewModel.onEvent(LogInUiEvent.Login(email = enteredEmail, password = enteredPassword))
    }


    private fun subscribeToViewStateUpdates() = with(binding) {
        collectLatest(viewModel.uiState) { state ->
            progressBar.isVisible = state.isLoading

            state.errorMessage?.getContentIfNotHandled()?.let {
                root.showSnackBar(requireContext().getString(it.toStringResId()))
            }

            if (state.shouldNavigateToConnections)
                findNavController().navigate(LogInFragmentDirections.logInToConnections())
        }
    }
}
