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
        val repository = spyk<HotFlowRepositoryImpl>()
        val viewModel = MyViewModel(repository)

        assertThat(viewModel.data.value).isEqualTo(0)
    }
}