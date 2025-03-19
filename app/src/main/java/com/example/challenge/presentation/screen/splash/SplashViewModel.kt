package com.example.challenge.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.domain.cache.PreferenceKeys
import com.example.challenge.domain.usecase.cache.ObserveCachedDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val observeCache: ObserveCachedDataUseCase
) : ViewModel() {

    private val _hasSavedSession = Channel<Boolean>()
    val hasSavedSession = _hasSavedSession.receiveAsFlow()

    init {
        readSession()
    }

    private fun readSession() = viewModelScope.launch {
        observeCache(key = PreferenceKeys.TOKEN)
            .collect { token ->
                if (token.isNullOrEmpty())
                    _hasSavedSession.send(element = false)
                else
                    _hasSavedSession.send(element = true)
            }
    }
}