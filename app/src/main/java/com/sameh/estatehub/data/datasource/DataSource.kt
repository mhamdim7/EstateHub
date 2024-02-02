package com.sameh.estatehub.data.datasource

import com.sameh.estatehub.data.NetworkResult
import com.sameh.estatehub.data.networkResult
import com.sameh.estatehub.data.api.ApiService
import com.sameh.estatehub.data.entity.EstateItemResponse
import com.sameh.estatehub.data.entity.EstateListingsResponse
import javax.inject.Inject

interface DataSource {
    suspend fun getEstateList(): NetworkResult<EstateListingsResponse>
    suspend fun getEstateItem(listingId: String): NetworkResult<EstateItemResponse>
}

class DataSourceImpl @Inject constructor(private val apiService: ApiService) : DataSource {
    override suspend fun getEstateList() = networkResult {
        apiService.getEstateList()
    }

    override suspend fun getEstateItem(listingId: String) = networkResult {
        apiService.getEstateItem(listingId)
    }

}

