package com.sameh.estatehub.mapper

import com.sameh.estatehub.data.entity.EstateItemResponse
import com.sameh.estatehub.data.entity.EstateListingsResponse
import com.sameh.estatehub.presentation.ui.EstateDetailsUiModel

object EstateMapper {
    fun estateDetailsUiModel(response: EstateItemResponse) =
        EstateDetailsUiModel(
            response.area,
            response.bedrooms,
            response.city,
            response.id,
            response.offerType,
            response.price,
            response.professional,
            response.propertyType,
            response.rooms,
            response.url
        )

    fun estateListUiModels(response: EstateListingsResponse) =
        response.estateItemResponses.map { item ->
            estateDetailsUiModel(item)
        }
}
