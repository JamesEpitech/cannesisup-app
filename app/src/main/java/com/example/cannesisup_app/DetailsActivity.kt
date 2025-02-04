package com.example.cannesisup_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        // Récupérer les éléments de l'interface
        val activityImage: ImageView = findViewById(R.id.activity_image)
        val activityName: TextView = findViewById(R.id.activity_name)
        val activityPrice: TextView = findViewById(R.id.activity_price)

        // Récupérer les données passées via l'intent
        val name: String? = intent.getStringExtra("activity_name")
        val price: String? = intent.getStringExtra("activity_price")
        val selectedChips = intent.getStringArrayListExtra("SELECTED_CHIPS")
        val city = intent.getStringExtra("CITY")

        val imageResId: Int = intent.getIntExtra("activity_image", R.drawable.default_image)
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, activitydetails::class.java)
            intent.putStringArrayListExtra("SELECTED_CHIPS", selectedChips)
            intent.putExtra("CITY", city)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        // Afficher les données dans les composants
        activityImage.setImageResource(imageResId)
        activityName.text = name
        activityPrice.text = price
    }
}