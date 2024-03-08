package com.maddy.practiceunittesting.presentation

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.domain.repository.HotFlowRepositoryImpl
import com.maddy.practiceunittesting.rules.MainDispatcherRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MyViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testHotFlowRepository() = runTest {
        // Arrange
        val repository = spyk<HotFlowRepositoryImpl>()
        val viewModel = MyViewModel(repository)

        // StateFlow should be considered as StateHolder
        // While testing always try to assert on the State of the StateFlow
        // because if you collect it as a flow and assert on that then you may miss some values.
        assertThat(viewModel.data.value).isEqualTo(0)

        // Act
        viewModel.initialize()

        // Assert
        repository.emit(1)
        assertThat(viewModel.data.value).isEqualTo(1)
    }


    /**
     *  Create a fake collector for testing purposes,
     *  - Just to make sure that we have someone collecting it,
     *  - so that stateIn will be active during the test.
     *  - We will launch a new coroutine to collect from the StateFlow and ignore all of its values.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLazilySharingViewModel() = runTest {
        val repository = spyk<HotFlowRepositoryImpl>()
        val viewModel = MyViewModel(repository)

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.dataUsingStateIn.collect()
        }

        assertThat(viewModel.dataUsingStateIn.value).isEqualTo(0)

        repository.emit(1)
        assertThat(viewModel.dataUsingStateIn.value).isEqualTo(1)
    }
}