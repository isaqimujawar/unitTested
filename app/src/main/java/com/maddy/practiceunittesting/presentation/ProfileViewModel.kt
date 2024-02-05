package com.maddy.practiceunittesting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maddy.practiceunittesting.domain.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {

    lateinit var user: String

    fun initialize() {
        viewModelScope.launch {
            user = repository.fetchData()
        }
    }

}