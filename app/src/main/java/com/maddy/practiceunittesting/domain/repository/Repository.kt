package com.maddy.practiceunittesting.domain.repository

import com.maddy.practiceunittesting.data.LocalDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(
    private val db: LocalDatabase = LocalDatabase(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val scope = CoroutineScope(ioDispatcher)


    suspend fun initialize() {
        db.populate()
    }

    suspend fun fetchData(): String = withContext(ioDispatcher) {
        db.read()
    }
}