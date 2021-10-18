package org.mmt_demo.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import org.mmt_demo.R
import org.mmt_demo.data.model.Flight
import org.mmt_demo.databinding.ActivityMainBinding
import org.mmt_demo.utils.Resource


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     lateinit var viewModel: FlightViewModel
   lateinit var binding: ActivityMainBinding
   lateinit var adapter : FlightAdapter
   var flightList = mutableListOf<Flight>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(FlightViewModel::class.java)
        setupRecyclerView()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.sort_by_price) {
            var tempList = flightList.sortedBy {
                it.ticketPrice
            }
            flightList.clear()
            flightList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }else{
            var tempList = flightList.sortedBy {
                it.Departure
            }
            flightList.clear()
            flightList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers() {
        viewModel.result.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data!!.flights.isNullOrEmpty()) {
                        flightList.clear()
                        flightList.addAll(it.data.flights)
                        adapter.notifyDataSetChanged()
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = FlightAdapter(flightList)
        binding.flightsRv.adapter = adapter
    }
}