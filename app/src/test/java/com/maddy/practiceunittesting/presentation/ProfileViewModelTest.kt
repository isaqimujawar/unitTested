package com.maddy.practiceunittesting.presentation

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.domain.repository.MyRepository
import com.maddy.practiceunittesting.domain.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

// Arrange

// Act

// Assert

class ProfileViewModelTest {

    @Test
    fun indirectExample() = runTest {
        // Arrange
        val viewModel = ProfileViewModel()

        // Act
        viewModel.initialize()

        // Assert
        assertThat(viewModel.user).isEqualTo("Alice")
    }

}