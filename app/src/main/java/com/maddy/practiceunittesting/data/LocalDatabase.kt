package com.maddy.practiceunittesting.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LocalDatabase {
    suspend fun populate() {
/*
        val scope = CoroutineScope(Dispatchers.IO)
        scope.async {
        // logic to populate the data from database
         }
*/
    }

    fun read(): String {
        return "Hello world"
    }
}