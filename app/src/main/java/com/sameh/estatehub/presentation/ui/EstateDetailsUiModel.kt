package com.sameh.estatehub.presentation.ui

data class EstateDetailsUiModel(
    val area: Double,
    val bedrooms: Int?,
    val city: String,
    val id: Int,
    val offerType: Int,
    val price: Double,
    val professional: String,
    val propertyType: String,
    val rooms: Int?,
    val url: String?
)