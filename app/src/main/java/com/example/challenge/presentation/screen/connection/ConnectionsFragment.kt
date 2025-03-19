package com.example.challenge.presentation.screen.connection

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge.databinding.FragmentConnectionsBinding
import com.example.challenge.presentation.core.BaseFragment
import com.example.challenge.presentation.core.toStringResId
import com.example.challenge.presentation.util.collectLatest
import com.example.challenge.presentation.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectionsFragment :
    BaseFragment<FragmentConnectionsBinding>(FragmentConnectionsBinding::inflate) {

    private val viewModel: ConnectionsViewModel by viewModels()

    override fun setup() {
        setupUI()
    }

    override fun listeners() = with(binding) {
        btnLogOut.setOnClickListener {
            viewModel.onEvent(ConnectionsUiEvent.LogOut)
        }
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        subscribeToViewStateUpdates(adapter)
    }

    private fun createAdapter(): ConnectionsAdapter {
        return ConnectionsAdapter()
    }

    private fun setupRecyclerView(connectionsAdapter: ConnectionsAdapter) {
        binding.rvConnections.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = connectionsAdapter
        }
    }

    private fun subscribeToViewStateUpdates(adapter: ConnectionsAdapter) = with(binding) {
        collectLatest(viewModel.uiState) { state ->
            adapter.submitList(state.connections)
            progressBar.isVisible = state.isLoading

            state.errorMessage?.getContentIfNotHandled()?.let {
                root.showSnackBar(requireContext().getString(it.toStringResId()))
            }

            if (state.shouldNavigateToLogin)
                findNavController().navigate(ConnectionsFragmentDirections.connectionsToLogIn())
        }
    }
}
