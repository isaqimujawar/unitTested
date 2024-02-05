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

    /**
     * Use async to start the coroutine and then use await() at the call site.
     * Because launch - Fire and Forget i.e., will start a Coroutine and forget.
     * async will start and await of the coroutine to finish the job and return a valur.
     * Using async you will know whether the coroutine actually finished the job.
     */
    fun initialize(): Deferred<Unit> {
        // scope.launch. { db.populate() }
        return scope.async { db.populate() }
    }

    suspend fun fetchData(): String = withContext(ioDispatcher) {
        db.read()
    }
}