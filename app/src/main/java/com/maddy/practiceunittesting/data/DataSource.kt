package com.maddy.practiceunittesting.data

import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun counts(): Flow<Int>
}