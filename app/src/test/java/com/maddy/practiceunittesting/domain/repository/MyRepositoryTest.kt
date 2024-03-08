package com.maddy.practiceunittesting.domain.repository

import com.google.common.truth.Truth.assertThat
import com.maddy.practiceunittesting.domain.repository.MyRepository
import com.maddy.practiceunittesting.domain.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
    // Arrange

    // Act

    // Assert
 */

class MyRepositoryTest {

    @Test
    fun dataIsHelloWorld() = runTest {
        // Arrange
        val repository = MyRepository()

        // Act
        val data = repository.fetchData()

        // Assert
        assertThat(data).isEqualTo("Hello world")
    }
}