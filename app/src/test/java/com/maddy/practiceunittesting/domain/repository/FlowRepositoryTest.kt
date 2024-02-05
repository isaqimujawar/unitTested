package com.maddy.practiceunittesting.domain.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.data.ColdDataSourceImpl
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class FlowRepositoryTest {

    @Test
    fun useTerminalOperators() = runTest {
        // Arrange
        val mockDataSource = mockk<ColdDataSourceImpl>()
        every { mockDataSource.counts() } answers { flowOf(1, 2, 3, 4, 5) }

        val repository = FlowRepository(mockDataSource)

        // Act
        val first = repository.scores().first()

        // Assert
        assertThat(first).isEqualTo(10)
    }
}