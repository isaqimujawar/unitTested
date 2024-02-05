package com.maddy.practiceunittesting.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ColdDataSourceImpl : DataSource {
    override fun counts(): Flow<Int> {
        return flowOf(1, 2, 3, 4, 5)
    }
}