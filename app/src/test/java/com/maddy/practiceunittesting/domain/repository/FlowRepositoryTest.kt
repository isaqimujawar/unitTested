package com.maddy.practiceunittesting.domain.repository

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.data.DataSource
import com.maddy.practiceunittesting.data.HotDataSourceImpl
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FlowRepositoryTest {

    @Test
    fun useTerminalOperators() = runTest {
        // Arrange
        val mockDataSource = mockk<DataSource>()
        every { mockDataSource.counts() } answers { flowOf(1, 2, 3, 4) }

        val repository = FlowRepository(mockDataSource)

        // Act and Assert
        val first = repository.scores().first()
        assertThat(first).isEqualTo(10)

        val values = repository.scores().toList()
        assertThat(values[0]).isEqualTo(10)
        assertThat(values[1]).isEqualTo(20)
        assertThat(values).hasSize(4)

        val someValues = repository.scores().take(2).toList()
        assertThat(someValues[0]).isEqualTo(10)
        assertThat(someValues[1]).isEqualTo(20)
        assertThat(someValues).hasSize(2)

        // Verify
        verify {
            mockDataSource.counts()
        }
    }


    @Test
    fun continuouslyCollect() = runTest {
        // Arrange
        val mockDataSource = spyk<HotDataSourceImpl>()
        val repository = FlowRepository(mockDataSource)
        val values = mutableListOf<Int>()

        // Using backgroundScope.launch{...} for coroutines that do not complete. Example for Hot Flows
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            // repository.scores().collect() { values += it }
            repository.scores().toList(values)
        }

        mockDataSource.emit(1)

        // Assert
        assertThat(values[0]).isEqualTo(10)

        coVerify(exactly = 1) {
            mockDataSource.emit(1)
        }
    }
}