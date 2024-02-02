package com.sameh.estatehub.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameh.estatehub.domain.resourceloader.ResourceState
import com.sameh.estatehub.domain.usecases.EstateItemUseCase
import com.sameh.estatehub.mapper.EstateMapper
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel
import com.sameh.estatehub.presentation.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstateDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val useCase: EstateItemUseCase
) : ViewModel() {

    private val _estateDetails = MutableStateFlow<ResourceState<EstateDetailsUiModel>>(
        ResourceState.Loading()
    )
    val estateDetails = _estateDetails.asStateFlow()


        init {
            val listingId = savedStateHandle[Routes.ESTATE_DETAILS_SCREEN_KEY] ?: ""
            fetchEstateDetails(listingId)
        }

    private fun fetchEstateDetails(listingId: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getEstateItem(listingId).collectLatest { resourceState ->
            _estateDetails.value = resourceState.mapSuccess {
                EstateMapper.estateDetailsUiModel(it)
            }
        }
    }

}