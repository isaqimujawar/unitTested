package com.maddy.practiceunittesting.domain.repository

import kotlinx.coroutines.flow.Flow

interface FlowRepository {
    fun scores(): Flow<Int>
}