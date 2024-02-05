package com.maddy.practiceunittesting.presentation

import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.*
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun testLoadMessage() = runTest {
        // Arrange
        val viewModel = HomeViewModel()

        // Act
        val result = viewModel.loadMessage()

        // Assert
        assertThat(result)
            .isEqualTo("Greetings!")
    }
}