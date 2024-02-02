package com.sameh.estatehub.domain

import com.sameh.estatehub.domain.resourceloader.ResourceState
import org.junit.Assert.assertTrue
import org.junit.Test

class ResourceStateTest {
    @Test
    fun `mapSuccess should transform Success state correctly`() {
        //given
        val originalData = "Original Data"
        val successState = ResourceState.Success(originalData)
        //when
        val transformedState = successState.mapSuccess { it.length }
        // then
        assertTrue(transformedState is ResourceState.Success && transformedState.data == 13)
    }

    @Test
    fun `mapSuccess should not affect Loading state`() {
        //given
        val loadingState = ResourceState.Loading<String>()
        //when
        val transformedState = loadingState.mapSuccess { it.length }
        // then
        assertTrue(transformedState is ResourceState.Loading)
    }

    @Test
    fun `mapSuccess should not affect Error state`() {
        //given
        val errorMessage = "An error occurred"
        val errorState = ResourceState.Error<String>(errorMessage)
        //when
        val transformedState = errorState.mapSuccess { it.length }
        // then
        assertTrue(transformedState is ResourceState.Error)
    }
}

