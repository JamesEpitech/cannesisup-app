package com.example.cannesisup_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TripAdapter(private val trips: List<TripInfo>, private val context: Context) : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {

    class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tripSentence)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        // Inflate the item_trip layout instead of app_page
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trip, parent, false)
        return TripViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = trips[position]
        holder.titleTextView.text = "Trip to ${trip.town}, from ${trip.startDate} to ${trip.endDate} with a budget of €${trip.budget}"

        holder.titleTextView.setOnClickListener {
            // Create an Intent to start the new activity
            val intent = Intent(context, activitydetails::class.java) // Replace with your actual activity class
            // Optionally, pass data to the new activity
            intent.putStringArrayListExtra("SELECTED_CHIPS", ArrayList(trip.selectedChips))
            intent.putExtra("CITY", trip.town)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return trips.size
    }
}