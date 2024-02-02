package com.sameh.estatehub

import com.sameh.estatehub.data.entity.EstateItemResponse
import com.sameh.estatehub.data.entity.EstateListingsResponse

object MockData {
    fun estateItemResponse(listingId: String = "1") = EstateItemResponse(
        1.1,
        1,
        "",
        listingId.toInt(),
        1,
        1.1,
        "",
        "",
        1,
        ""
    )

    fun estateListingsResponse() = listOf(estateItemResponse()).let {
        EstateListingsResponse(it, it.size)
    }
}