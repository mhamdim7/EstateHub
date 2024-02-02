package com.sameh.estatehub.domain.usecases

import com.sameh.estatehub.data.datasource.DataSource
import com.sameh.estatehub.domain.resourceloader.resourceFlow
import javax.inject.Inject

class EstateListUseCase @Inject constructor(private val dataSource: DataSource) {

    suspend fun getEstateList() = resourceFlow { dataSource.getEstateList() }
}
