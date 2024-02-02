package com.sameh.estatehub.presentation


import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.sameh.estatehub.MockData
import com.sameh.estatehub.data.entity.EstateListingsResponse
import com.sameh.estatehub.domain.resourceloader.ResourceState
import com.sameh.estatehub.domain.usecases.EstateListUseCase
import com.sameh.estatehub.mapper.EstateMapper
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel
import com.sameh.estatehub.presentation.viewmodel.EstateListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EstateListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var useCase: EstateListUseCase

    @Before
    fun setUp() {
        useCase = mockk()
    }

    @Test
    fun `fetch estateList - success`() = runBlocking {
        // Given
        val response = MockData.estateListingsResponse()
        val responseFlow = MutableStateFlow(
            ResourceState.Success(response)
        )
        val expected = EstateMapper.estateListUiModels(response).let {
            ResourceState.Success(it)
        }
        coEvery { useCase.getEstateList() } returns responseFlow
        // When
        val viewModel = EstateListViewModel(useCase)
        delay(50)
        // Then
        val actualState = viewModel.estateList.value
        assertEquals(actualState, expected)
    }

    @Test
    fun `fetch estateList - loading`() = runBlocking {
        // Given
        val responseFlow = MutableStateFlow(
            ResourceState.Loading<EstateListingsResponse>()
        )
        coEvery { useCase.getEstateList() } returns responseFlow
        // When
        val viewModel = EstateListViewModel(useCase)
        // Then
        val actualState = viewModel.estateList.value
        assertTrue(actualState is ResourceState.Loading<List<EstateDetailsUiModel>>)
    }

    @Test
    fun `fetch estateList - error`() = runBlocking {
        // Given
        val error = "Some error message"
        val responseFlow = MutableStateFlow<ResourceState<EstateListingsResponse>>(
            ResourceState.Error(error)
        )
        coEvery { useCase.getEstateList() } returns responseFlow
        // When
        val viewModel = EstateListViewModel(useCase)
        delay(50)
        // Then
        val actualState = viewModel.estateList.value
        assertEquals(ResourceState.Error<List<EstateDetailsUiModel>>(error), actualState)
    }
}