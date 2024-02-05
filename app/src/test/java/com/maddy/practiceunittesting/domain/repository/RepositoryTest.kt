package com.maddy.practiceunittesting.domain.repository

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.data.LocalDatabase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verifySequence
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RepositoryTest {

    @Test
    fun repoTest() = runTest {
        // Arrange
        // val spyDb = spyk<LocalDatabase>()    // Works as a spy
        val mockDb = mockk<LocalDatabase>(relaxUnitFun = true)      // relaxUnitFun = true; pre-stubs functions that return Unit
        val repository = Repository(
            db = mockDb,
            ioDispatcher = StandardTestDispatcher(testScheduler)    // always share Scheduler
        )
        // coEvery { mockDb.populate() } returns Unit       // no need to stub this after setting relaxUnitFun = true
        every { mockDb.read() } answers {"Hello world"}

        // Act
        repository.initialize()
        val data = repository.fetchData()
        advanceUntilIdle()

        // Assert
        assertThat(data).isEqualTo("Hello world")

        // Using coVerifySequence{} to verify the order of function calls,
        // and that only these functions are called
        coVerifySequence {
            mockDb.populate()
            mockDb.read()
        }
    }
}