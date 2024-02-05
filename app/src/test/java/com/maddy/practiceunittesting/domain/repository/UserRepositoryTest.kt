package com.maddy.practiceunittesting.domain.repository

import com.google.common.truth.Truth
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserRepositoryTest {
    @Test
    fun directExampleWithStandardTestDispatcher() = runTest {
        // Arrange
        val repo = UserRepository()

        // Act
        launch { repo.register("Alice") }
        launch { repo.register("Bob") }
        advanceUntilIdle()

        // Assert
        Truth.assertThat(repo.getAllUsers())
            .containsExactly("Alice", "Bob")
            .inOrder()
    }

    @Test
    fun directExampleWithUnconfinedTestDispatcher() = runTest(UnconfinedTestDispatcher()) {
        // Arrange
        val repo = UserRepository()

        // Act
        launch { repo.register("Alice") }
        launch { repo.register("Bob") }

        // Assert
        Truth.assertThat(repo.getAllUsers())
            .containsExactly("Alice", "Bob")
            .inOrder()
    }
}