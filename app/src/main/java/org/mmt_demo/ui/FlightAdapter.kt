package org.mmt_demo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mmt_demo.data.model.Flight
import org.mmt_demo.databinding.ItemFlightBinding

class FlightAdapter(private val items: List<Flight>) : RecyclerView.Adapter<FlightAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFlightBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: ItemFlightBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Flight) {
            binding.flight = item
            binding.executePendingBindings()
        }
    }
}
