package com.maddy.practiceunittesting.domain.repository

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.data.DataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
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


        // Verify
        verify {
            mockDataSource.counts()
        }
    }
}

/*

* */