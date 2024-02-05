package com.maddy.practiceunittesting.repository

import kotlinx.coroutines.delay

class MyRepository {
    suspend fun fetchData(): String {
        delay(1000L)
        return "Hello world"
    }
}