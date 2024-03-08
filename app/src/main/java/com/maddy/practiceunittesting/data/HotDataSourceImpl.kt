package com.maddy.practiceunittesting.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class HotDataSourceImpl : DataSource {
    private val flow = MutableSharedFlow<Int>()

    suspend fun emit(value: Int) = flow.emit(value)

    override fun counts(): Flow<Int> = flow
}