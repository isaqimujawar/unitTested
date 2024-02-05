package com.maddy.practiceunittesting.domain.repository

import com.maddy.practiceunittesting.data.ColdDataSourceImpl
import com.maddy.practiceunittesting.data.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlowRepository(private val dataSource: DataSource = ColdDataSourceImpl()) {
    fun scores(): Flow<Int> {
        return dataSource.counts().map { it * 10 }
    }
}