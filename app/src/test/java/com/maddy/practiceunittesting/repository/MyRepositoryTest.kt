package com.maddy.practiceunittesting.repository

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class MyRepositoryTest {

    @Test
    fun dataIsHelloWorld() {
        // Arrange
        val repository = MyRepository()

        // Act
        // val data = repository.fetchData()
        val data = "Hello world"

        // Assert
        assertThat(data).isEqualTo("Hello world")
    }
}