package com.maddy.practiceunittesting.domain.repository

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.data.LocalDatabase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RepositoryTest {

    @Test
    fun repoTest() = runTest {
        // Arrange
        val mockDb = mockk<LocalDatabase>()
        val repository = Repository(
            db = mockDb,
            ioDispatcher = StandardTestDispatcher(testScheduler)    // always share Scheduler
        )
        every { mockDb.populate() } returns Unit
        every { mockDb.read() } returns "Hello world"

        // Act
        repository.initialize()
        val data = repository.fetchData()

        // Assert
        assertThat(data).isEqualTo("Hello world")
    }
}