package com.sameh.estatehub.di

import android.content.Context
import com.sameh.estatehub.data.NetworkInterceptor
import com.sameh.estatehub.data.api.ApiService
import com.sameh.estatehub.data.datasource.DataSource
import com.sameh.estatehub.data.datasource.DataSourceImpl
import com.sameh.estatehub.domain.usecases.EstateItemUseCase
import com.sameh.estatehub.domain.usecases.EstateListUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(@ApplicationContext appContext: Context): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val httpClient = okhttp3.OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor(appContext))
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDataSource(apiService: ApiService): DataSource {
        return DataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesEstateItemUseCase(dataSource: DataSource): EstateItemUseCase {
        return EstateItemUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesEstateListUseCase(dataSource: DataSource): EstateListUseCase {
        return EstateListUseCase(dataSource)
    }

    companion object {
        private const val BASE_URL = "https://gsl-apps-technical-test.dignp.com/"
    }

}