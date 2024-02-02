package com.sameh.estatehub.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.sameh.estatehub.MockData
import com.sameh.estatehub.data.entity.EstateItemResponse
import com.sameh.estatehub.domain.resourceloader.ResourceState
import com.sameh.estatehub.domain.usecases.EstateItemUseCase
import com.sameh.estatehub.mapper.EstateMapper
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel
import com.sameh.estatehub.presentation.ui.navigation.Routes
import com.sameh.estatehub.presentation.viewmodel.EstateDetailsViewModel
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

class EstateDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var useCase: EstateItemUseCase
    private val listingId = "1"
    private val savedStateHandle =
        SavedStateHandle(mapOf(Routes.ESTATE_DETAILS_SCREEN_KEY to listingId))

    @Before
    fun setUp() {
        useCase = mockk()
    }

    @Test
    fun `fetch estate details - success`() = runBlocking {
        // Given
        val response = MockData.estateItemResponse(listingId)
        val responseFlow = MutableStateFlow(
            ResourceState.Success(response)
        )
        val expected = EstateMapper.estateDetailsUiModel(response).let {
            ResourceState.Success(it)
        }
        coEvery { useCase.getEstateItem(listingId) } returns responseFlow
        // When
        val viewModel = EstateDetailsViewModel(savedStateHandle, useCase)
        delay(50)
        // Then
        val actualState = viewModel.estateDetails.value
        assertEquals(actualState, expected)
    }

    @Test
    fun `fetch estate details - loading`() = runBlocking {
        // Given
        val responseFlow = MutableStateFlow(
            ResourceState.Loading<EstateItemResponse>()
        )
        coEvery { useCase.getEstateItem(listingId) } returns responseFlow
        // When
        val viewModel = EstateDetailsViewModel(savedStateHandle, useCase)
        // Then
        val actualState = viewModel.estateDetails.value
        assertTrue(actualState is ResourceState.Loading<EstateDetailsUiModel>)
    }

    @Test
    fun `fetch estate details - error`() = runBlocking {
        // Given
        val error = "Some error message"
        val responseFlow = MutableStateFlow<ResourceState<EstateItemResponse>>(
            ResourceState.Error(error)
        )
        coEvery { useCase.getEstateItem(listingId) } returns responseFlow
        // When
        val viewModel = EstateDetailsViewModel(savedStateHandle, useCase)
        delay(50)
        // Then
        val actualState = viewModel.estateDetails.value
        assertEquals(ResourceState.Error<EstateDetailsUiModel>(error), actualState)
    }
}
