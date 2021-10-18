package org.mmt_demo.data.remote

import javax.inject.Inject

class FlightRemoteDataSource @Inject constructor(
    private val flightService: FlightService
): BaseDataSource() {

    suspend fun getFlights() = getResult { flightService.getAllFlights() }
}