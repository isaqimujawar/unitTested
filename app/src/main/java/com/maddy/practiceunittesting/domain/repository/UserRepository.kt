package com.maddy.practiceunittesting.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepository {
    private val _listOfUsers = MutableStateFlow<List<String>>(emptyList())
    val listOfUsers: StateFlow<List<String>> = _listOfUsers.asStateFlow()

    fun register(name: String) {
        val newList = listOfUsers.value.toMutableList()
        newList.add(name)
        _listOfUsers.value = newList
    }

    fun getAllUsers() = listOfUsers.value

    fun fetchData(): String {
        return listOfUsers.value.first()

    }
}