package com.sameh.estatehub.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.sameh.estatehub.R
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel
import com.sameh.estatehub.presentation.ui.theme.dimens


@Composable
fun EstateList(items: List<EstateDetailsUiModel>, onUserClicked: (listingId: String) -> Unit) {
    LazyColumn {
        items(
            count = items.size,
            key = { items[it].id },
            itemContent = { EstateItem(items[it], onUserClicked) }
        )
    }
}


@Composable
private fun EstateItem(
    estate: EstateDetailsUiModel,
    onUserClicked: (listingId: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.small)
            .clickable { onUserClicked(estate.id.toString()) },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            AsyncImage(
                placeholder = rememberVectorPainter(image = Icons.Outlined.Warning),
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                model = estate.url,
                contentDescription = stringResource(id = R.string.image_content_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.estateItemImageHeight)
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small))
            EstateEntryItem(title = stringResource(R.string.city), value = estate.city)
            EstateEntryItem(title = stringResource(R.string.property_type), value = estate.propertyType)
            estate.bedrooms?.let {
                EstateEntryItem(title = stringResource(R.string.bedrooms), value = estate.bedrooms.toString())
            }
            EstateEntryItem(title = stringResource(R.string.professional), value = estate.professional)
            EstateEntryItem(title = stringResource(R.string.area), value = stringResource(R.string.sqm, estate.area))
            EstateEntryItem(title = stringResource(R.string.price), value = estate.price.toString())
            estate.rooms?.let {
                EstateEntryItem(title = stringResource(R.string.rooms), value = estate.rooms.toString())
            }
        }
    }
}

@Composable
 private fun EstateEntryItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimens.small))
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}
