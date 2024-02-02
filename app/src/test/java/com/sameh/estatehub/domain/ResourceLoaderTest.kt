package com.sameh.estatehub.domain

import com.sameh.estatehub.data.NetworkResult
import com.sameh.estatehub.domain.resourceloader.ResourceState
import com.sameh.estatehub.domain.resourceloader.resourceFlow
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ResourceLoaderTest {

    @Test
    fun `resourceFlow emits Loading and Success for successful network call`() = runTest {
        //given
        val successResult = NetworkResult.Success("Mocked data")
        val block: suspend () -> NetworkResult<String> = mockk {
            coEvery { this@mockk.invoke() } returns successResult
        }

        // when
        resourceFlow(block).collect { state ->
            //then
            when (state) {
                is ResourceState.Loading -> assertEquals(
                    ResourceState.Loading::class.java,
                    state.javaClass
                )

                is ResourceState.Success -> {
                    assertEquals(ResourceState.Success::class.java, state.javaClass)
                    assertEquals(successResult.data, state.data)
                }

                is ResourceState.Error -> throw AssertionError("Expected Loading or Success, but got Error")
            }
        }
    }

    @Test
    fun `resourceFlow emits Loading and Error for failed network call`() = runTest {
        //given
        val errorMessage = "Mocked error"
        val errorResult = NetworkResult.Error(500, errorMessage)
        val block: suspend () -> NetworkResult<String> = mockk {
            coEvery { this@mockk.invoke() } returns errorResult
        }
        // when
        resourceFlow(block).collect { state ->
            //then
            when (state) {
                is ResourceState.Loading -> assertEquals(
                    ResourceState.Loading::class.java,
                    state.javaClass
                )

                is ResourceState.Success -> throw AssertionError("Expected Loading or Error, but got Success")
                is ResourceState.Error -> {
                    assertEquals(ResourceState.Error::class.java, state.javaClass)
                    assertEquals(errorMessage, state.message)
                }
            }
        }
    }
}
