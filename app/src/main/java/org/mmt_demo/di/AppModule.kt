package org.mmt_demo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mmt_demo.data.remote.FlightRemoteDataSource
import org.mmt_demo.data.remote.FlightService
import org.mmt_demo.data.repository.FlightRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideFlightService(retrofit: Retrofit): FlightService = retrofit.create(FlightService::class.java)

    @Singleton
    @Provides
    fun provideFlightRemoteDataSource(flightService: FlightService) = FlightRemoteDataSource(flightService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: FlightRemoteDataSource) =
        FlightRepository(remoteDataSource)
}