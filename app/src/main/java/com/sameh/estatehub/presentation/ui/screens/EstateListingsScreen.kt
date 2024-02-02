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
import com.sameh.estatehub.presentation.ui.components.EmptyStateComponent
import com.sameh.estatehub.presentation.ui.components.ErrorComponent
import com.sameh.estatehub.presentation.ui.components.EstateList
import com.sameh.estatehub.presentation.ui.components.ProgressLoader
import com.sameh.estatehub.presentation.viewmodel.EstateListViewModel

@Composable
fun EstateListingsScreen(
    navigateToDetails: (listingId: String) -> Unit,
    viewModel: EstateListViewModel = hiltViewModel()
) {

    val estateList by viewModel.estateList.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (val estates = estateList) {

                is ResourceState.Loading -> ProgressLoader()

                is ResourceState.Success -> if (estates.data.isEmpty()) EmptyStateComponent()
                else EstateList(estates.data) {
                    navigateToDetails(it)
                }

                is ResourceState.Error -> {
                    ErrorComponent(estates.message)
                }
            }
        }
    }
}