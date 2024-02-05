package com.maddy.practiceunittesting.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class HotFlowRepositoryImpl : FlowRepository {
    private val flow = MutableSharedFlow<Int>()

    suspend fun emit(value: Int) = flow.emit(value)

    override fun scores(): Flow<Int> = flow
}