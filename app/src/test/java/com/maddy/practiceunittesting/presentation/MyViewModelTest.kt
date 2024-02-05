package com.maddy.practiceunittesting.presentation

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.domain.repository.HotFlowRepositoryImpl
import com.maddy.practiceunittesting.rules.MainDispatcherRule
import io.mockk.spyk
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
}