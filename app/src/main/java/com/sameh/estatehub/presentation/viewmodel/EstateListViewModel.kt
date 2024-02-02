package com.sameh.estatehub.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameh.estatehub.domain.resourceloader.ResourceState
import com.sameh.estatehub.domain.usecases.EstateListUseCase
import com.sameh.estatehub.mapper.EstateMapper.estateListUiModels
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstateListViewModel @Inject constructor(private val useCase: EstateListUseCase) :
    ViewModel() {

    private val _estateList = MutableStateFlow<ResourceState<List<EstateDetailsUiModel>>>(
        ResourceState.Loading()
    )
    val estateList = _estateList.asStateFlow()

    init {
        getEstateList()
    }

    private fun getEstateList() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getEstateList().collectLatest {
            _estateList.value = it.mapSuccess { response ->
                estateListUiModels(response)
            }
        }
    }


}
