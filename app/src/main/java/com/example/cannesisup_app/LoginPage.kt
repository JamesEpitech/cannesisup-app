package com.example.cannesisup_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private var usernameInputValue: String = ""
private var ageInputValue: String = ""
private var genderInputValue: String = ""
private var emailInputValue: String = ""

class Page2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.loginpage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loginButton: Button = findViewById(R.id.loginButton)
        val usernameInput: EditText = findViewById(R.id.daysInput)
        val ageInput: EditText = findViewById(R.id.groupSizeInput)
        val genderInput: EditText = findViewById(R.id.GenderInput)
        val emailInput: EditText = findViewById(R.id.emailInput)
        loginButton.setOnClickListener {
            if (validateInputs(usernameInput, ageInput, genderInput, emailInput)) {
                usernameInputValue = usernameInput.text.toString()
                GlobalState.username = usernameInputValue
                ageInputValue = ageInput.text.toString()
                genderInputValue = genderInput.text.toString()
                emailInputValue = emailInput.text.toString()
                Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, app_page::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            } else {
                Toast.makeText(this, "Please fill in everything.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validateInputs(usernameInput: EditText, ageInput: EditText, genderInput: EditText, emailInput: EditText): Boolean {
        return usernameInput.text.isNotEmpty() && ageInput.text.isNotEmpty() && genderInput.text.isNotEmpty() && emailInput.text.isNotEmpty()
    }
}