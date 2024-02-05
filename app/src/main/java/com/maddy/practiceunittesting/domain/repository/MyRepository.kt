package com.maddy.practiceunittesting.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MyRepository {
    suspend fun fetchData(): String = withContext(Dispatchers.IO){
        delay(1000L)
        "Hello world"
    }
}