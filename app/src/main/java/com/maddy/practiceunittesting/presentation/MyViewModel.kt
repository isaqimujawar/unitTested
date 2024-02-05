package com.maddy.practiceunittesting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maddy.practiceunittesting.domain.repository.FlowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: FlowRepository
) : ViewModel() {
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