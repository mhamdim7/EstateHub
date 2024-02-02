package com.sameh.estatehub.data.api

import com.sameh.estatehub.data.entity.EstateItemResponse
import com.sameh.estatehub.data.entity.EstateListingsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("listings.json")
    suspend fun getEstateList(): Response<EstateListingsResponse>

    @GET("listings/{listingId}.json")
    suspend fun getEstateItem(@Path("listingId") listingId: String): Response<EstateItemResponse>

}