package com.maddy.practiceunittesting.presentation

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.rules.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

/**
 * There can be only one Scheduler in the TestScope:
 *  - If you replace the Main dispatcher with a TestDispatcher by calling setMain(),
 *  - any TestDispatchers created after that point in time will look at Main first
 *  - and if there is a TestDispatcher already, then they will grab its Scheduler and share it.
 *  - Therefore, all new TestDispatchers will automatically share its Scheduler.
 */
class HomeViewModelTestUsingRule {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun testGreeting() = runTest {
        val viewModel = HomeViewModel()
        viewModel.loadMessage()
        assertThat(viewModel.message.value).containsMatch("Greetings!")
    }
}