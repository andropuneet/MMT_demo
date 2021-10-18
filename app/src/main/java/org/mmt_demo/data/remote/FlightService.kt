package org.mmt_demo.data.remote

import org.mmt_demo.data.model.FlightList
import retrofit2.Response
import retrofit2.http.GET

interface FlightService {
    @GET("v3/ec830f3d-6523-40c7-80d8-e9da5b5efc27")
    suspend fun getAllFlights() : Response<FlightList>
}