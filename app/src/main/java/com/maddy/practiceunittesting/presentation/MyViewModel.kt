package com.maddy.practiceunittesting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maddy.practiceunittesting.domain.repository.FlowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * stateIn operator can do the exact same thing with a lot less code.
 * Converts a cold Flow into a hot StateFlow that is started in the given coroutine scope,
 * sharing the most recently emitted value from a single running instance of the upstream flow
 * with multiple downstream subscribers.
 * See the StateFlow documentation for the general concepts of state flows.
 */
class MyViewModel(
    private val repository: FlowRepository
) : ViewModel() {
    val dataUsingStateIn: StateFlow<Int> = repository.scores()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), 0)

    private val _data = MutableStateFlow(0)
    val data: StateFlow<Int> = _data.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            repository.scores().collect() { score ->
                _data.value = score
            }
        }
    }
}