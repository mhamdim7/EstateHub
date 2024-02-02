package com.sameh.estatehub.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sameh.estatehub.R
import com.sameh.estatehub.presentation.ui.theme.dimens


@Composable
fun ProgressLoader() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(MaterialTheme.dimens.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
        Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium))
        Text(
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun EmptyStateComponent() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_items_found),
            style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ErrorComponent(message: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.error_text, message),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}