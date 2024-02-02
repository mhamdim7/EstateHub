package com.sameh.estatehub.presentation.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sameh.estatehub.R
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel
import com.sameh.estatehub.presentation.ui.theme.dimens

@Composable
fun RealEstateDetail(estate: EstateDetailsUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.medium),
        shape = MaterialTheme.shapes.medium
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.small)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = stringResource(id = R.string.image_content_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimens.imageSize)
                        .clip(shape = MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium))
            }
            item {
                DetailItem(title = stringResource(R.string.city), value = estate.city)
            }
            item {
                DetailItem(
                    title = stringResource(R.string.property_type),
                    value = estate.propertyType
                )
            }
            estate.rooms?.let {
                item {
                    DetailItem(
                        title = stringResource(R.string.rooms),
                        value = estate.rooms.toString()
                    )
                }
            }
            item {
                DetailItem(title = stringResource(R.string.price), value = "$${estate.price}")
            }
            item {
                DetailItem(
                    title = stringResource(R.string.area), value = stringResource(
                        R.string.sqm,
                        estate.area
                    )
                )
            }
            item {
                DetailItem(
                    title = stringResource(R.string.professional),
                    value = estate.professional
                )
            }
            estate.bedrooms?.let {
                item {
                    DetailItem(
                        title = stringResource(R.string.bedrooms),
                        value = estate.bedrooms.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun DetailItem(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.small)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.verySmall))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}