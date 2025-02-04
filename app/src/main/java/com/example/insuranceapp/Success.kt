package com.example.insuranceapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.insuranceapp.database.DatabaseHelper

class Success : AppCompatActivity() {

    private lateinit var dbh: DatabaseHelper
    val username1 = LoginActivity.edituser.text.toString()

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success)

        dbh = DatabaseHelper(this) // Initialize the DatabaseHelper

        val cursor = dbh.getText()
        if (cursor != null && cursor.moveToFirst()) {
            val usernameColumnIndex = cursor.getColumnIndex("username")
            val emailColumnIndex = cursor.getColumnIndex("Email")

            var foundUsername = false

            do {
                val username = cursor.getString(usernameColumnIndex)
                val email = cursor.getString(emailColumnIndex)

                if (username ==username1) {
                    foundUsername = true
                    val textView1 = findViewById<TextView>(R.id.textView1)
                    textView1.text = "Username: $email"
                    break // Exit the loop if the desired username is found
                }
            } while (cursor.moveToNext())

            if (!foundUsername) {
                Toast.makeText(this, "No contact found for the provided username", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No contacts available", Toast.LENGTH_SHORT).show()
        }
        val textView = findViewById<TextView>(R.id.txtLink)
        textView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val button = findViewById<Button>(R.id.Profile)
        button.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.Addclaim)
        button2.setOnClickListener {
            val intent = Intent(this, ClaimsActivity::class.java)
            startActivity(intent)
        }

        val button3 = findViewById<Button>(R.id.AddAsset)
        button3.setOnClickListener {
            val intent = Intent(this, AddAsset::class.java)
            startActivity(intent)
        }
    }
}
