package org.mmt_demo.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.mmt_demo.data.model.Flight
import org.mmt_demo.data.repository.FlightRepository
import java.util.*
import javax.inject.Inject
import kotlin.Comparator

@HiltViewModel
class FlightViewModel @Inject constructor(
    val repository: FlightRepository
) : ViewModel() {
    var result = liveData {
        emit(repository.getFlights())
    }
}