package com.maddy.practiceunittesting.presentation

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.domain.repository.UserRepository
import com.maddy.practiceunittesting.rules.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

// Arrange

// Act

// Assert

class ProfileViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun indirectExample() = runTest {
        // Arrange
        val mockRepository = mockk<UserRepository>()
        every { mockRepository.register("Alice") } returns Unit
        every { mockRepository.fetchData() } answers { "Alice" }

        val viewModel = ProfileViewModel()

        // Act
        viewModel.initialize()

        // Assert
        assertThat(viewModel.user).isEqualTo("Alice")
    }

}