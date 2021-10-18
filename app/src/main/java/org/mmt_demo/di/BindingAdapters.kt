package org.mmt_demo.di

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.mmt_demo.data.model.Flight
import java.text.SimpleDateFormat
import java.util.*


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
    @JvmStatic
    @BindingAdapter("setTime")
    fun bindTime(textView: TextView, time: String) {
        val format = SimpleDateFormat("yyyy-mm-dd HH:mm")
        val date1: Date = format.parse(time)
        var newFormat = SimpleDateFormat("hh:mm aa");
        var formattedDate = newFormat.format(date1)
        textView.text = formattedDate
    }
    @JvmStatic
    @BindingAdapter("setAmount")
    fun bindAmount(textView: TextView, amount: String) {
        textView.text = "\u20B9".plus(" ").plus(amount)
    }
    @JvmStatic
    @BindingAdapter("setTimeDiff")
    fun bindTimeDiff(textView: TextView, flight: Flight) {
        val format = SimpleDateFormat("yyyy-mm-dd HH:mm")
        val date1: Date = format.parse(flight.Departure)
        val date2: Date = format.parse(flight.Arrival)
        val millis = date2.time - date1.time
        val diff = "${(millis / (1000 * 60 * 60))}h ${(millis / (1000 * 60) % 60)}m"
        textView.text = diff
    }
}