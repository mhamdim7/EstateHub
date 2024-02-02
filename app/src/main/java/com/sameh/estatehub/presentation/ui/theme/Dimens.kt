package com.sameh.estatehub.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimens(
    val verySmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val veryLarge: Dp = 36.dp,
    //
    val imageSize: Dp = 200.dp,
    val estateItemImageHeight: Dp = 160.dp
)

val MaterialTheme.dimens: Dimens
    get() = Dimens()