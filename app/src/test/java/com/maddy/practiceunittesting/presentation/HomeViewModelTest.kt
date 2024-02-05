package com.maddy.practiceunittesting.presentation

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

class HomeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGreetings() = runTest {
        // Arrange
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)    // share the Scheduler
        Dispatchers.setMain(testDispatcher)     // Sets the given dispatcher as an underlying dispatcher of Dispatchers.Main.

        try {
            val viewModel = HomeViewModel()
            viewModel.loadMessage()
            assertThat(viewModel.message.value).isEqualTo("Greetings!")
        } finally {
            Dispatchers.resetMain()         // Resets state of the Dispatchers.Main to the original main dispatcher.
        }
    }
}