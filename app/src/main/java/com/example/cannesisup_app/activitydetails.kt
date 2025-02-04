package com.example.cannesisup_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activitydetails : AppCompatActivity() {
    // Liste complète des activités
    private val allActivities = arrayOf(
        "Se détendre à la plage de la Croisette",
        "Découvrir les Îles de Lérins",
        "Randonner à la Croix des Gardes",
        "Faire du kayak et du paddle",
        "Participer au Festival de Cannes",
        "Participer à un atelier de permaculture",
        "Planter avec des locaux lors d'un atelier",
        "Créer des objets lors d'ateliers DIY 0 déchet",
        "Visiter la Villa Domergue",
        "Explorer un parcours d'arts urbains",
        "Se promener sur le Vieux-Port",
        "Naviguer lors d'une promenade en bateau",
        "Créer lors d'ateliers de céramique"
    )

    // Images associées aux activités (R.drawable.image_name)
    private val activityImages = intArrayOf(
        R.drawable.plage_croisette,
        R.drawable.iles_lerins,
        R.drawable.croix_des_gardes,
        R.drawable.kayak_paddle,
        R.drawable.festival_cannes,
        R.drawable.permaculture,
        R.drawable.plantation_atelier,
        R.drawable.diy_zero_dechet,
        R.drawable.villa_domergue,
        R.drawable.arts_urbains,
        R.drawable.vieux_port,
        R.drawable.promenade_bateau,
        R.drawable.atelier_ceramique
    )
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activitydetails)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val selectedChips = intent.getStringArrayListExtra("SELECTED_CHIPS") ?: arrayListOf()
        val city = intent.getStringExtra("CITY") ?: "Unknown City" // Provide a default value

        // Display the selected chips
        val chipsTextView: TextView = findViewById(R.id.interestsText)
        chipsTextView.text = "Activities centered around: ${selectedChips?.joinToString(", ")}"
        val cityText: TextView = findViewById(R.id.countryTitle)
        cityText.text = city
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, app_page::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        val activityList: ListView = findViewById(R.id.activityList)

        // Mélanger la liste des activités et en sélectionner 3 au hasard
        val shuffledActivities = allActivities.toMutableList()
        shuffledActivities.shuffle() // Mélanger aléatoirement

        val randomActivities = if (shuffledActivities.size >= 3) {
            shuffledActivities.subList(0, 3)
        } else {
            shuffledActivities // Use all available activities if less than 3
        }

        // Afficher les 3 activités sélectionnées
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, randomActivities)
        activityList.adapter = adapter
        activityList.visibility = View.VISIBLE

        // Gérer les clics sur les activités
        activityList.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetailsActivity::class.java)

            // Récupérer l'index de l'activité dans la liste d'origine
            val originalIndex = allActivities.indexOf(randomActivities[position])

            // Envoyer les informations à l'écran de détails
            intent.putExtra("activity_name", randomActivities[position])
            intent.putExtra("activity_price", "${(position + 1) * 10}€")
            if (originalIndex >= 0 && originalIndex < activityImages.size) {
                intent.putExtra("activity_image", activityImages[originalIndex]) // Image associée
            } else {
                // Handle the case where the index is invalid
                intent.putExtra("activity_image", R.drawable.default_image) // Use a default image
            }
            intent.putStringArrayListExtra("SELECTED_CHIPS", selectedChips)
            intent.putExtra("CITY", city)

            startActivity(intent)
        }
    }
}