package com.maddy.practiceunittesting.domain.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.data.DataSource
import com.maddy.practiceunittesting.data.HotDataSourceImpl
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ColdFlowRepositoryImplTest {

    @Test
    fun useTerminalOperators() = runTest {
        // Arrange
        val mockDataSource = mockk<DataSource>()
        every { mockDataSource.counts() } answers { flowOf(1, 2, 3, 4) }

        val repository = ColdFlowRepositoryImpl(mockDataSource)

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
        val repository = ColdFlowRepositoryImpl(mockDataSource)
        val values = mutableListOf<Int>()

        // Using backgroundScope.launch{...} for coroutines that do not complete. Example for Hot Flows
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            // repository.scores().collect() { values += it }
            repository.scores().toList(values)
        }

        mockDataSource.emit(1)
        assertThat(values[0]).isEqualTo(10)

        mockDataSource.emit(2)
        mockDataSource.emit(3)
        assertThat(values.last()).isEqualTo(30)
        assertThat(values).hasSize(3)

        coVerify(exactly = 1) {
            mockDataSource.emit(1)
            mockDataSource.emit(2)
            mockDataSource.emit(3)
        }
    }

    @Test
    fun continuouslyCollectWithTurbine() = runTest {
        // Arrange
        val mockDataSource = spyk<HotDataSourceImpl>()
        val repository = ColdFlowRepositoryImpl(mockDataSource)

        val values: Flow<Int> = repository.scores()

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            values.test {
                mockDataSource.emit(1)
                assertThat(awaitItem()).isEqualTo(10)
                mockDataSource.emit(2)
                assertThat(expectMostRecentItem()).isEqualTo(20)
            }
        }
    }
}