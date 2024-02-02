package com.sameh.estatehub.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sameh.estatehub.domain.resourceloader.ResourceState
import com.sameh.estatehub.presentation.ui.components.ErrorComponent
import com.sameh.estatehub.presentation.ui.components.ProgressLoader
import com.sameh.estatehub.presentation.ui.components.RealEstateDetail
import com.sameh.estatehub.presentation.viewmodel.EstateDetailsViewModel

@Composable
fun EstateDetailsScreen(
    viewModel: EstateDetailsViewModel = hiltViewModel()
) {
    val estateDetails by viewModel.estateDetails.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            when (val details = estateDetails) {
                is ResourceState.Loading -> ProgressLoader()
                is ResourceState.Success -> RealEstateDetail(details.data)
                is ResourceState.Error -> ErrorComponent(message = details.message)
            }
        }
    }
}
