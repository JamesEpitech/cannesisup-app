package com.example.cannesisup_app

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.size
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlin.random.Random

@Suppress("DEPRECATION")
class addtrip : AppCompatActivity() {
    private lateinit var startDateTextView: TextView
    private lateinit var endDateTextView: TextView
    private lateinit var startDateButton: Button
    private lateinit var endDateButton: Button

    private var startDate: Calendar = Calendar.getInstance()
    private var endDate: Calendar = Calendar.getInstance()
    private lateinit var budgetInput: EditText
    private lateinit var townInput: EditText
    private lateinit var groupSizeInput: EditText
    private lateinit var chipGroup: ChipGroup
    private lateinit var startDateInput: String
    var selectedChips = mutableListOf<String>()
    private lateinit var endDateInput: String
    private lateinit var registerTripButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.addtrip)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        startDateTextView = findViewById(R.id.startDateTextView)
        endDateTextView = findViewById(R.id.endDateTextView)
        startDateButton = findViewById(R.id.startDateButton)
        endDateButton = findViewById(R.id.endDateButton)
        budgetInput = findViewById(R.id.budgetInput)
        townInput = findViewById(R.id.townInput)
        groupSizeInput = findViewById(R.id.groupSizeInput)
        chipGroup = findViewById(R.id.chipGroup)
        registerTripButton = findViewById(R.id.registerTripButton)

        startDateButton.setOnClickListener {
            showDatePickerDialog(true)
        }
        endDateButton.setOnClickListener {
            showDatePickerDialog(false)
        }
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)

        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            chip.setOnClickListener {
                if (chip.chipBackgroundColor == ColorStateList.valueOf(resources.getColor(R.color.chip_selected))) {
                    chip.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.chip_default))
                    selectedChips.remove(chip.text.toString())
                } else {
                    chip.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.chip_selected))
                    selectedChips.add(chip.text.toString())
                }
            }
        }
        val registerTripButton: Button = findViewById(R.id.registerTripButton)
        registerTripButton.setOnClickListener {
            registerTrip()
            val intent = Intent(this, app_page::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            Toast.makeText(this, "Trip has been registered! You saved 135g of CO2!", Toast.LENGTH_LONG).show()
        }
    }
    private fun registerTrip() {
        val budget = budgetInput.text.toString()
        val groupSize = groupSizeInput.toString()
        val town = townInput.text.toString()
        val tripInfo = TripInfo(budget, selectedChips, startDateInput, endDateInput, groupSize,town)
        GlobalState.trips.add(tripInfo)
    }
    @SuppressLint("SetTextI18n")
    private fun showDatePickerDialog(isStartDate: Boolean) {
        val calendar = if (isStartDate) startDate else endDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, selectedYear)
                set(Calendar.MONTH, selectedMonth)
                set(Calendar.DAY_OF_MONTH, selectedDay)
            }

            if (isStartDate) {
                startDate = selectedDate
                startDateInput = formatDate(selectedDate)
                startDateTextView.text = "Start Date: ${formatDate(selectedDate)}"
            } else {
                endDate = selectedDate
                endDateInput = formatDate(selectedDate)
                endDateTextView.text = "End Date: ${formatDate(selectedDate)}"
            }
        }, year, month, day)

        datePickerDialog.show()
    }
    private fun formatDate(calendar: Calendar): String {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-based
        val year = calendar.get(Calendar.YEAR)
        return "$day/$month/$year"
    }
}