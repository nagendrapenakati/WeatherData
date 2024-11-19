package com.example.weatherapp.di

import com.example.weatherapp.data.api.WeatherService
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) : WeatherService{
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideMapper() : WeatherMapper{
        return WeatherMapper()
    }

    @Provides
    @Singleton
    fun provideRepository(weatherService: WeatherService,weatherMapper: WeatherMapper) : WeatherRepository{
        return WeatherRepositoryImpl(weatherService,weatherMapper)
    }

}