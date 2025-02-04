package com.example.cannesisup_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class app_page : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tripAdapter: TripAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.app_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tripsText: TextView = findViewById(R.id.tripsTitle)
        tripsText.text = "${GlobalState.username}'s Trips"
        val addTrip: ImageView = findViewById(R.id.newTripButton)
        recyclerView = findViewById(R.id.recyclerViewTrips)
        recyclerView.layoutManager = LinearLayoutManager(this) // Set the layout manager
        val trips = GlobalState.trips
        tripAdapter = TripAdapter(trips, this)
        recyclerView.adapter = tripAdapter
        addTrip.setOnClickListener {
            val intent = Intent(this, addtrip::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
    fun openActivity()
    {

    }
}