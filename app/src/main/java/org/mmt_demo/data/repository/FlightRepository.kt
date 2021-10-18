package org.mmt_demo.data.repository

import org.mmt_demo.data.remote.FlightRemoteDataSource
import javax.inject.Inject

class FlightRepository @Inject constructor(
    private val remoteDataSource: FlightRemoteDataSource,
) {
    suspend fun getFlights() = remoteDataSource.getFlights()
}