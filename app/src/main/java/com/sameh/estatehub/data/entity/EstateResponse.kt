package com.sameh.estatehub.data.entity


import com.squareup.moshi.Json

data class EstateListingsResponse(
    @Json(name = "items")
    val estateItemResponses: List<EstateItemResponse>,
    @Json(name = "totalCount")
    val totalCount: Int
)

data class EstateItemResponse(
    @Json(name = "area")
    val area: Double,
    @Json(name = "bedrooms")
    val bedrooms: Int?,
    @Json(name = "city")
    val city: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "offerType")
    val offerType: Int,
    @Json(name = "price")
    val price: Double,
    @Json(name = "professional")
    val professional: String,
    @Json(name = "propertyType")
    val propertyType: String,
    @Json(name = "rooms")
    val rooms: Int?,
    @Json(name = "url")
    val url: String?
)