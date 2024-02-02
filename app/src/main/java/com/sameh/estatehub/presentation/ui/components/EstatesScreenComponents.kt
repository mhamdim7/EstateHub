package com.sameh.estatehub.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.sameh.estatehub.R
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel
import com.sameh.estatehub.presentation.ui.theme.dimens


@Composable
fun EstateList(items: List<EstateDetailsUiModel>, onUserClicked: (listingId: String) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(MaterialTheme.dimens.medium)) {
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
            .wrapContentHeight()
            .padding(vertical = MaterialTheme.dimens.small)
            .clickable { onUserClicked(estate.id.toString()) },
        shape = MaterialTheme.shapes.medium
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val image = ConstrainedLayoutReference(stringResource(R.string.image))
            val column = ConstrainedLayoutReference(stringResource(R.string.column))
            val overlayColor = if (isSystemInDarkTheme()) Color.Black else Color.White
            AsyncImage(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(column.top)
                        bottom.linkTo(column.bottom)
                        start.linkTo(column.start)
                        end.linkTo(column.end)
                        width = Dimension.matchParent
                        height = Dimension.fillToConstraints
                    },
                placeholder = rememberVectorPainter(image = Icons.Outlined.Warning),
                error = painterResource(id = R.drawable.ic_image_error),
                model = estate.url,
                contentDescription = stringResource(id = R.string.image_content_description),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .constrainAs(column) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxSize()
                    .background(overlayColor.copy(alpha = 0.4f))
            )
            {
                EstateEntryItem(
                    title = stringResource(R.string.area),
                    value = stringResource(R.string.sqm, estate.area)
                )
                EstateEntryItem(
                    title = stringResource(R.string.price),
                    value = estate.price.toString()
                )
                estate.rooms?.let {
                    EstateEntryItem(
                        title = stringResource(R.string.rooms),
                        value = estate.rooms.toString()
                    )
                }
                EstateEntryItem(title = stringResource(R.string.city), value = estate.city)
                EstateEntryItem(
                    title = stringResource(R.string.property_type),
                    value = estate.propertyType
                )
                estate.bedrooms?.let {
                    EstateEntryItem(
                        title = stringResource(R.string.bedrooms),
                        value = estate.bedrooms.toString()
                    )
                }
                EstateEntryItem(
                    title = stringResource(R.string.professional),
                    value = estate.professional
                )
            }
        }
    }
}

@Composable
private fun EstateEntryItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.verySmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(MaterialTheme.dimens.verySmall))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}
