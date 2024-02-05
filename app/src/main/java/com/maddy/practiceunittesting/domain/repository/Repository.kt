package com.maddy.practiceunittesting.domain.repository

import com.maddy.practiceunittesting.data.LocalDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(
    private val db: LocalDatabase = LocalDatabase()
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    fun initialize() {
        scope.launch { db.populate() }
    }

    suspend fun fetchData(): String = withContext(Dispatchers.IO) {
        db.read()
    }
}